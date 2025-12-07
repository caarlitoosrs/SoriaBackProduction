package com.experienciassoria.service;

import com.experienciassoria.dto.comentario.*;
import com.experienciassoria.model.*;
import com.experienciassoria.repository.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

        private final ComentarioRepository comentarioRepository;
        private final ExperienciaRepository experienciaRepository;
        private final UsuarioRepository usuarioRepository;

        public ComentarioService(
                        ComentarioRepository comentarioRepository,
                        ExperienciaRepository experienciaRepository,
                        UsuarioRepository usuarioRepository) {
                this.comentarioRepository = comentarioRepository;
                this.experienciaRepository = experienciaRepository;
                this.usuarioRepository = usuarioRepository;
        }

        // 🔹 Obtener comentarios de una experiencia
        public List<ComentarioDTO> getComentariosByExperiencia(UUID experienciaId) {
                Experiencia experiencia = experienciaRepository.findById(experienciaId)
                                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada"));

                return comentarioRepository.findByExperienciaOrderByFechaDesc(experiencia).stream()
                                .map(c -> new ComentarioDTO(
                                                c.getId(),
                                                c.getUsuario().getNombre(),
                                                c.getTexto(),
                                                c.getFecha(),
                                                c.getUsuario().getFotoPerfilUrl(),
                                                c.getUsuario().getId()))
                                .collect(Collectors.toList());
        }

        // 🔹 Crear un nuevo comentario
        public ComentarioDTO crearComentario(UUID usuarioId, UUID experienciaId, String request) {
                Usuario usuario = usuarioRepository.findById(usuarioId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                Experiencia experiencia = experienciaRepository.findById(experienciaId)
                                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada"));

                Comentario comentario = Comentario.builder()
                                .usuario(usuario)
                                .experiencia(experiencia)
                                .texto(request)
                                .fecha(Instant.now())
                                .build();

                comentarioRepository.save(comentario);

                return new ComentarioDTO(
                                comentario.getId(),
                                usuario.getNombre(),
                                comentario.getTexto(),
                                comentario.getFecha(),
                                usuario.getFotoPerfilUrl(),
                                comentario.getUsuario().getId());
        }

        // 🔹 Actualizar comentario
        public ComentarioDTO actualizarComentario(UUID comentarioId, UUID usuarioId, String nuevoTexto) {
                Usuario usuario = usuarioRepository.findById(usuarioId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                Comentario comentario = comentarioRepository.findById(comentarioId)
                                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

                // Validar autor
                if (!comentario.getUsuario().getId().equals(usuarioId)) {
                        throw new RuntimeException("No tienes permisos para editar este comentario");
                }

                comentario.setTexto(nuevoTexto);
                comentarioRepository.save(comentario);

                return new ComentarioDTO(
                                comentario.getId(),
                                comentario.getUsuario().getNombre(),
                                comentario.getTexto(),
                                comentario.getFecha(),
                                usuario.getFotoPerfilUrl(),
                                comentario.getUsuario().getId());
        }

        // 🔹 Eliminar comentario
        public void eliminarComentario(UUID comentarioId, UUID usuarioId) {

                Comentario comentario = comentarioRepository.findById(comentarioId)
                                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

                // Validar autor
                if (!comentario.getUsuario().getId().equals(usuarioId)) {
                        throw new RuntimeException("No tienes permisos para eliminar este comentario");
                }

                comentarioRepository.delete(comentario);
        }

        public UUID getUsuarioIdByEmail(String email) {
                return usuarioRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
                                .getId();
        }

}
