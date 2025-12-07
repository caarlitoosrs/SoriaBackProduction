package com.experienciassoria.service;

import com.experienciassoria.dto.experiencia.*;
import com.experienciassoria.exception.ResourceNotFoundException;
import com.experienciassoria.model.Experiencia;
import com.experienciassoria.model.ExperienciaUID;
import com.experienciassoria.repository.ExperienciaRepository;
import com.experienciassoria.repository.ExperienciaUIDRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExperienciaService {

        private final ExperienciaRepository experienciaRepository;
        private final ExperienciaUIDRepository experienciaUIDRepository;

        public ExperienciaService(ExperienciaRepository experienciaRepository,
                        ExperienciaUIDRepository experienciaUIDRepository) {
                this.experienciaRepository = experienciaRepository;
                this.experienciaUIDRepository = experienciaUIDRepository;
        }

        // 🔹 Obtener todas las experiencias (ADMIN, paginadas)
        public List<ExperienciaListDTO> getAllExperiencias(int offset, int limit) {
                return experienciaRepository.findAll(PageRequest.of(offset, limit)).stream()
                                .map(exp -> new ExperienciaListDTO(
                                                exp.getId(),
                                                exp.getTitulo(),
                                                exp.getCategoria().name(),
                                                exp.getImagenPortadaUrl(),
                                                exp.isActivo()))
                                .collect(Collectors.toList());
        }

        // 🔹 Obtener experiencias Activos (USUARIOS)
        public List<ExperienciaListDTO> getAllActivoExperiencias(int offset, int limit) {
                return experienciaRepository.findByActivoTrue(PageRequest.of(offset, limit)).stream()
                                .map(exp -> new ExperienciaListDTO(
                                                exp.getId(),
                                                exp.getTitulo(),
                                                exp.getCategoria().name(),
                                                exp.getImagenPortadaUrl(),
                                                exp.isActivo()))
                                .collect(Collectors.toList());
        }

        // 🔹 Obtener detalle
        public ExperienciaDetailDTO getExperienciaById(UUID id) {
                Experiencia exp = experienciaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada"));
                return new ExperienciaDetailDTO(
                                exp.getId(),
                                exp.getTitulo(),
                                exp.getDescripcion(),
                                exp.getCategoria().name(),
                                exp.getImagenPortadaUrl(),
                                exp.getGaleriaImagenes() != null ? exp.getGaleriaImagenes() : new ArrayList<>(),
                                exp.getDireccion(),
                                exp.getUbicacionLat(),
                                exp.getUbicacionLng(),
                                exp.getPuntosOtorgados() != null ? exp.getPuntosOtorgados() : 10,
                                exp.isActivo());
        }

        // 🔹 Crear experiencia
        public ExperienciaDetailDTO crearExperiencia(CrearExperienciaRequest request) {
                Experiencia exp = Experiencia.builder()
                                .titulo(request.getTitulo())
                                .descripcion(request.getDescripcion())
                                .categoria(Experiencia.Categoria.valueOf(request.getCategoria()))
                                .imagenPortadaUrl(request.getImagenPortadaUrl())
                                .galeriaImagenes(request.getGaleriaImagenes() != null ? request.getGaleriaImagenes()
                                                : new ArrayList<>())
                                .direccion(request.getDireccion())
                                .ubicacionLat(request.getUbicacionLat())
                                .ubicacionLng(request.getUbicacionLng())
                                .puntosOtorgados(
                                                request.getPuntosOtorgados() != null && request.getPuntosOtorgados() > 0
                                                                ? request.getPuntosOtorgados()
                                                                : 10)
                                .activo(true)
                                .build();
                experienciaRepository.save(exp);
                return getExperienciaById(exp.getId());
        }

        // 🔹 Actualizar experiencia
        public ExperienciaDetailDTO updateExperiencia(UUID id, CrearExperienciaRequest request) {
                Experiencia exp = experienciaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada"));
                exp.setTitulo(request.getTitulo());
                exp.setDescripcion(request.getDescripcion());
                exp.setCategoria(Experiencia.Categoria.valueOf(request.getCategoria()));
                exp.setImagenPortadaUrl(request.getImagenPortadaUrl());
                exp.setDireccion(request.getDireccion());
                exp.setUbicacionLat(request.getUbicacionLat());
                exp.setUbicacionLng(request.getUbicacionLng());
                exp.setGaleriaImagenes(request.getGaleriaImagenes());
                exp.setPuntosOtorgados(request.getPuntosOtorgados());
                exp.setActivo(request.getActivo());
                experienciaRepository.save(exp);
                return getExperienciaById(id);
        }

        // 🔹 Soft delete
        public void softDeleteExperiencia(UUID id) {
                Experiencia exp = experienciaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Experiencia no encontrada"));
                exp.setActivo(false);
                experienciaRepository.save(exp);
        }

        // 🔹 Obtener UIDs de una experiencia (solo ADMIN)
        public List<ExperienciaUIDDTO> getUidsByExperiencia(UUID experienciaId) {
                Experiencia experiencia = experienciaRepository.findById(experienciaId)
                                .orElseThrow(() -> new ResourceNotFoundException("Experiencia no encontrada"));

                return experienciaUIDRepository.findByExperiencia(experiencia).stream()
                                .map(uid -> new ExperienciaUIDDTO(
                                                uid.getId(),
                                                uid.getUid(),
                                                uid.isActivo(),
                                                uid.getFechaGeneracion() // Mapea fechaGeneracion del modelo a
                                                                         // fechaCreacion del DTO
                                ))
                                .collect(Collectors.toList());
        }

        // 🔹 Obtener experiencia por UID (público)
        public ExperienciaDetailDTO getExperienciaByUid(String uid) {
                com.experienciassoria.model.ExperienciaUID experienciaUID = experienciaUIDRepository
                                .findByUidAndActivoTrue(uid)
                                .orElseThrow(() -> new ResourceNotFoundException("UID no encontrado o no activo"));

                Experiencia experiencia = experienciaUID.getExperiencia();
                return getExperienciaById(experiencia.getId());
        }
}
