package com.experienciassoria.controller;

import com.experienciassoria.dto.comentario.*;
import com.experienciassoria.security.JwtUtils;
import com.experienciassoria.service.ComentarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/experiencias/{experienciaId}/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;
    private final JwtUtils jwtUtils;

    public ComentarioController(ComentarioService comentarioService, JwtUtils jwtUtils) {
        this.comentarioService = comentarioService;
        this.jwtUtils = jwtUtils;
    }

    // 🔹 GET /api/experiencias/{id}/comentarios — obtener comentarios
    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> getComentarios(@PathVariable UUID experienciaId) {
        return ResponseEntity.ok(comentarioService.getComentariosByExperiencia(experienciaId));
    }

    // 🔹 POST /api/experiencias/{id}/comentarios — crear un comentario
    @PostMapping
    public ResponseEntity<ComentarioDTO> crearComentario(
            @PathVariable UUID experienciaId,
            @RequestBody CrearComentarioRequest request,
            HttpServletRequest httpRequest) {

        // Obtener email del usuario a partir del token
        String token = jwtUtils.getTokenFromRequest(httpRequest);
        String email = jwtUtils.getEmailFromToken(token);

        // Obtener el ID del usuario
        UUID usuarioId = comentarioService.getUsuarioIdByEmail(email);

        // Crear comentario
        ComentarioDTO nuevoComentario = comentarioService.crearComentario(usuarioId, experienciaId, request.getTexto());

        return ResponseEntity.ok(nuevoComentario);
    }

    @PutMapping("/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actualizarComentario(
            @PathVariable UUID experienciaId,
            @PathVariable UUID comentarioId,
            @RequestBody ActualizarComentarioRequest request,
            HttpServletRequest httpRequest) {

        String token = jwtUtils.getTokenFromRequest(httpRequest);
        String email = jwtUtils.getEmailFromToken(token);
        UUID usuarioId = comentarioService.getUsuarioIdByEmail(email);

        ComentarioDTO actualizado = comentarioService.actualizarComentario(
                comentarioId,
                usuarioId,
                request.getTexto());

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<Void> eliminarComentario(
            @PathVariable UUID experienciaId,
            @PathVariable UUID comentarioId,
            HttpServletRequest httpRequest) {

        String token = jwtUtils.getTokenFromRequest(httpRequest);
        String email = jwtUtils.getEmailFromToken(token);
        UUID usuarioId = comentarioService.getUsuarioIdByEmail(email);

        comentarioService.eliminarComentario(comentarioId, usuarioId);

        return ResponseEntity.noContent().build();
    }

}
