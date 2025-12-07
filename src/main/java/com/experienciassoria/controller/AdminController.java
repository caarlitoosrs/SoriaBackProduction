package com.experienciassoria.controller;

import com.experienciassoria.dto.auth.UsuarioDto;
import com.experienciassoria.dto.auth.CreateUserRequest;
import com.experienciassoria.dto.auth.UpdateUserRequest;
import com.experienciassoria.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Obtener todos los usuarios
    @GetMapping
    public List<UsuarioDto> getAllUsers() {
        return userService.getAllUsers();
    }

    // Obtener usuario por email
    @GetMapping("/{email}")
    public UsuarioDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Actualizar usuario por email
    @PutMapping("/{email}")
    public UsuarioDto updateUser(@PathVariable String email, @RequestBody UpdateUserRequest request) {
        return userService.updateUserByEmail(email, request);
    }

    // Eliminar usuario por email
    // @PreAuthorize("hasRole('ADMIN')")
    // @DeleteMapping("/{email}")
    // public void deleteUser(@PathVariable String email) {
    //     userService.deleteUserByEmail(email);
    // }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UsuarioDto createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

        // 🔹 DELETE /api/admin/usuarios/{id} — eliminar usuario
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable UUID id) {
        userService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
