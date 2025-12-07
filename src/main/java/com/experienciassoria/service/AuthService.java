package com.experienciassoria.service;

import com.experienciassoria.dto.auth.*;
import com.experienciassoria.exception.ResourceNotFoundException;
import com.experienciassoria.model.Usuario;
import com.experienciassoria.repository.UsuarioRepository;
import com.experienciassoria.security.JwtUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .activo(true)
                .fechaCreacion(Instant.now())
                .role(Usuario.Rol.USER)
                .build();

        usuarioRepository.save(usuario);

        String token = jwtUtils.generateToken(usuario.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtils.generateToken(usuario.getEmail());
        return new AuthResponse(token);
    }

    public UsuarioDto getMe(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRole().name(),
                usuario.getPuntos(), usuario.getFotoPerfilUrl(), usuario.isActivo());
    }

    public UsuarioDto updateUser(String email, UpdateUserRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar los campos que vengan en la request
        if (request.getNombre() != null && !request.getNombre().isEmpty()) {
            usuario.setNombre(request.getNombre());
        }

        if (request.getEmail() != null && !request.getEmail().isEmpty()
                && !request.getEmail().equals(usuario.getEmail())) {
            if (usuarioRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("El email ya está registrado");
            }
            usuario.setEmail(request.getEmail());
        }

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getFotoPerfilUrl() != null && !request.getFotoPerfilUrl().isEmpty()) {
            usuario.setFotoPerfilUrl(request.getFotoPerfilUrl());
        }
        Usuario updated = usuarioRepository.save(usuario);

        return new UsuarioDto(
                updated.getId(),
                updated.getNombre(),
                updated.getEmail(),
                updated.getRole().name(),
                updated.getPuntos(),
                updated.getFotoPerfilUrl(),
            updated.isActivo());
    }

    public boolean emailExists(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Transactional
    public UsuarioDto actualizarFotoPerfil(String email, String fotoPerfilUrl) {
        log.info("Actualizando foto de perfil para usuario: {}", email);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        usuario.setFotoPerfilUrl(fotoPerfilUrl);
        usuarioRepository.save(usuario);
        log.info("Foto de perfil actualizada exitosamente para usuario: {}", email);

        return new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRole().name(),
                usuario.getPuntos(), usuario.getFotoPerfilUrl(), usuario.isActivo());
    }

}
