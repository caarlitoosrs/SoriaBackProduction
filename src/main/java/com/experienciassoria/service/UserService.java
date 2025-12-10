package com.experienciassoria.service;

import com.experienciassoria.dto.auth.CreateUserRequest;
import com.experienciassoria.dto.auth.UpdateUserRequest;
import com.experienciassoria.dto.auth.UsuarioDto;
import com.experienciassoria.dto.comentario.ComentarioDTO;
import com.experienciassoria.dto.pasaporte.PasaporteDTO;
import com.experienciassoria.dto.pasaporte.RegistroExperienciaDTO;
import com.experienciassoria.exception.ResourceNotFoundException;
import com.experienciassoria.exception.ValidationException;
import com.experienciassoria.model.Usuario;
import com.experienciassoria.model.Usuario.Rol;
import com.experienciassoria.repository.ComentarioRepository;
import com.experienciassoria.repository.RegistroExperienciaRepository;
import com.experienciassoria.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ComentarioRepository comentarioRepository;
    private final PasaporteService pasaporteService;
    private final RegistroExperienciaRepository registroRepository;

    public UserService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
            RegistroExperienciaRepository registroRepository,
            ComentarioRepository comentarioRepository,
            PasaporteService pasaporteService) {
        this.registroRepository = registroRepository;
        this.comentarioRepository = comentarioRepository;
        this.pasaporteService = pasaporteService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioDto> getAllUsers() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioDto getUserByEmail(String email) {
        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toDto(user);
    }

    public UsuarioDto updateUserByEmail(String email, UpdateUserRequest request) {
        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (request.getNombre() != null && !request.getNombre().isEmpty()) {
            user.setNombre(request.getNombre());
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty()
                && !request.getEmail().equals(user.getEmail())) {
            if (usuarioRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("El email ya está registrado");
            }
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getRole() != null && !request.getRole().isEmpty()) {
            user.setRole(Rol.valueOf(request.getRole()));
        }

        if (request.getFotoPerfilUrl() != null && !request.getFotoPerfilUrl().isEmpty()) {
            user.setFotoPerfilUrl(request.getFotoPerfilUrl());
        } 

        if (request.getActivo() != null) {
            user.setActivo(request.getActivo());
        }

        usuarioRepository.save(user);
        return toDto(user);
    }

    public void deleteUserByEmail(String email) {
        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepository.delete(user);
    }

    private UsuarioDto toDto(Usuario user) {
        return new UsuarioDto(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getRole().name(),
                user.getPuntos(),
                user.getFotoPerfilUrl(), 
                user.isActivo());
    }

    public UsuarioDto createUser(CreateUserRequest request) {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setRole(Rol.valueOf(request.getRole()));
        usuario.setPuntos(request.getPuntos() != null ? request.getPuntos() : 0);
        usuario.setFotoPerfilUrl(request.getFotoPerfilUrl());

        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        usuarioRepository.save(usuario);

        return toDto(usuario);
    }

    @Transactional
    public void eliminarUsuario(UUID id) {
        log.info("Eliminando usuario: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (usuario.getRole() == Usuario.Rol.ADMIN) {
            long totalAdmins = usuarioRepository.findAll().stream()
                    .filter(u -> u.getRole() == Usuario.Rol.ADMIN && u.isActivo())
                    .count();

            if (totalAdmins <= 1) {
                throw new ValidationException("No se puede eliminar el último administrador del sistema");
            }
        }

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
        log.info("Usuario {} marcado como inactivo", id);
    }

    public PasaporteDTO getPasaporteUsuario(UUID id) {
        log.info("Obteniendo pasaporte del usuario: {}", id);
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        return pasaporteService.getPasaporte(id);
    }

    public List<RegistroExperienciaDTO> getExperienciasUsuario(UUID id) {
        log.info("Obteniendo experiencias del usuario: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return registroRepository.findByUsuario(usuario).stream()
                .map(r -> new RegistroExperienciaDTO(
                        r.getExperiencia().getId(),
                        r.getExperiencia().getTitulo(),
                        r.getExperiencia().getCategoria().name(),
                        r.getFechaRegistro(),
                        r.getOpinion(),
                        r.getImgPortada(),
                        r.getPuntosOtorgados()))
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> getComentariosUsuario(UUID id) {
        log.info("Obteniendo comentarios del usuario: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return comentarioRepository.findByUsuarioOrderByFechaDesc(usuario).stream()
                .map(c -> new ComentarioDTO(
                        c.getId(),
                        c.getUsuario().getNombre(),
                        c.getTexto(),
                        c.getFecha(),
                        c.getUsuario().getFotoPerfilUrl(),
                        usuario.getId()))
                .collect(Collectors.toList());
    }

}
