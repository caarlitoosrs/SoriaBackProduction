package com.experienciassoria.controller;

import com.experienciassoria.dto.pasaporte.*;
import com.experienciassoria.security.JwtUtils;
import com.experienciassoria.service.ComentarioService;
import com.experienciassoria.service.PasaporteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/pasaporte")
public class PasaporteController {

    private final PasaporteService pasaporteService;
    private final JwtUtils jwtUtils;
    private final ComentarioService comentarioService;

    public PasaporteController(PasaporteService pasaporteService, JwtUtils jwtUtils, ComentarioService comentarioService) {
        this.pasaporteService = pasaporteService;
        this.jwtUtils = jwtUtils;
        this.comentarioService = comentarioService;
    }
    
    @GetMapping
    public ResponseEntity<PasaporteDTO> getPasaporte(HttpServletRequest request) {
        String token = extractToken(request);
        String email = jwtUtils.getEmailFromToken(token);

        UUID usuarioId = pasaporteService
                .getUsuarioIdByEmail(email); 
        PasaporteDTO pasaporte = pasaporteService.getPasaporte(usuarioId);

        return ResponseEntity.ok(pasaporte);
    }

    
    @PostMapping("/registrar")
    public ResponseEntity<RegistroExperienciaDTO> registrarExperiencia(
            HttpServletRequest request,
            @RequestBody RegistroRequest registroRequest
    ) {
        String token = extractToken(request);
        String email = jwtUtils.getEmailFromToken(token);

        UUID usuarioId = pasaporteService
                .getUsuarioIdByEmail(email);
        RegistroExperienciaDTO registro = pasaporteService.registrarExperiencia(usuarioId, registroRequest);
        
        return ResponseEntity.ok(registro);
    }

    
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Token no encontrado o inválido");
        }
        return header.substring(7);
    }
}
