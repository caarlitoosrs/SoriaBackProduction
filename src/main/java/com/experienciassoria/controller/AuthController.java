package com.experienciassoria.controller;

import com.experienciassoria.dto.auth.*;
import com.experienciassoria.security.JwtUtils;
import com.experienciassoria.service.AuthService;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthService authService, JwtUtils jwtUtils) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioDto> getMe(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = header.substring(7);
        String email = jwtUtils.getEmailFromToken(token);
        return ResponseEntity.ok(authService.getMe(email));
    }

    @PutMapping("/me")
    public ResponseEntity<UsuarioDto> updateMe(
            HttpServletRequest request,
            @RequestBody UpdateUserRequest updateRequest) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = header.substring(7);
        String email = jwtUtils.getEmailFromToken(token);

        UsuarioDto updated = authService.updateUser(email, updateRequest);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/check-email")
    public Map<String, Boolean> checkEmail(@RequestParam String email) {
        boolean exists = authService.emailExists(email);
        return Collections.singletonMap("exists", exists);
    }

    @PutMapping("/me/foto-perfil")
    public ResponseEntity<UsuarioDto> actualizarFotoPerfil(
            @Valid @RequestBody ActualizarFotoPerfilRequest request,
            HttpServletRequest httpRequest) {
        String token = jwtUtils.extractTokenFromRequest(httpRequest);
        String email = jwtUtils.getEmailFromToken(token);
        return ResponseEntity.ok(authService.actualizarFotoPerfil(email, request.getFotoPerfilUrl()));
    }
}
