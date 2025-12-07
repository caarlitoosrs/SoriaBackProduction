package com.experienciassoria.service;

import com.experienciassoria.dto.pasaporte.*;
import com.experienciassoria.model.*;
import com.experienciassoria.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PasaporteService {

        private final RegistroExperienciaRepository registroRepo;
        private final ExperienciaUIDRepository experienciaUIDRepo;
        private final UsuarioRepository usuarioRepo;
        private final ExperienciaRepository experienciaRepo;
        private final ComentarioService comentarioService;

        public PasaporteService(
                        RegistroExperienciaRepository registroRepo,
                        ExperienciaUIDRepository experienciaUIDRepo,
                        UsuarioRepository usuarioRepo,
                        ExperienciaRepository experienciaRepo,
                        ComentarioService comentarioService) {
                this.registroRepo = registroRepo;
                this.experienciaUIDRepo = experienciaUIDRepo;
                this.usuarioRepo = usuarioRepo;
                this.experienciaRepo = experienciaRepo;
                this.comentarioService = comentarioService;
        }

        public UUID getUsuarioIdByEmail(String email) {
                return usuarioRepo.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"))
                                .getId();
        }

        // 🔹 Obtener pasaporte completo de un usuario
        public PasaporteDTO getPasaporte(UUID usuarioId) {
                Usuario usuario = usuarioRepo.findById(usuarioId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                List<RegistroExperienciaDTO> registros = registroRepo.findByUsuario(usuario).stream()
                                .map(r -> new RegistroExperienciaDTO(
                                                r.getExperiencia().getId(),
                                                r.getExperiencia().getTitulo(),
                                                r.getExperiencia().getCategoria().name(),
                                                r.getFechaRegistro(),
                                                r.getOpinion(),
                                                r.getImgPortada(),
                                                r.getPuntosOtorgados()))
                                .collect(Collectors.toList());

                return new PasaporteDTO(usuario.getId(), usuario.getNombre(), usuario.getPuntos(), registros);
        }

        // 🔹 Registrar una experiencia (a partir de un UID)
        @Transactional
        public RegistroExperienciaDTO registrarExperiencia(UUID usuarioId, RegistroRequest request) {
                Usuario usuario = usuarioRepo.findById(usuarioId)
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

                ExperienciaUID experienciaUID = experienciaUIDRepo.findByUidAndActivoTrue(request.getUidScaneado())
                                .orElseThrow(() -> new RuntimeException("UID inválido o no activo"));

                Experiencia experiencia = experienciaUID.getExperiencia();

                comentarioService.crearComentario(usuarioId, experiencia.getId(), request.getOpinion());

                if (registroRepo.existsByUsuario_IdAndExperiencia_Id(usuarioId, experiencia.getId())) {
                        throw new RuntimeException("La experiencia ya fue registrada por este usuario");
                }
                int puntosOtorgados = experiencia.getPuntosOtorgados() != null ? experiencia.getPuntosOtorgados() : 10;

                RegistroExperiencia registro = RegistroExperiencia.builder()
                                .usuario(usuario)
                                .experiencia(experiencia)
                                .experienciaUID(experienciaUID)
                                .imgPortada(experiencia.getImagenPortadaUrl())
                                .opinion(request.getOpinion())
                                .fechaRegistro(Instant.now())
                                .puntosOtorgados(puntosOtorgados)
                                .build();

                registroRepo.save(registro);

                // 🔸 sumar puntos al usuario
                usuario.setPuntos(usuario.getPuntos() + registro.getPuntosOtorgados());
                usuarioRepo.save(usuario);

                return new RegistroExperienciaDTO(
                                experiencia.getId(),
                                experiencia.getTitulo(),
                                experiencia.getCategoria().name(),
                                registro.getFechaRegistro(),
                                registro.getImgPortada(),
                                registro.getOpinion(),
                                registro.getPuntosOtorgados());
        }
}
