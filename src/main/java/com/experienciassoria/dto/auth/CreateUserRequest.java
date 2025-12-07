package com.experienciassoria.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String nombre;
    private String email;
    private String password;
    private String role;
    private Integer puntos;
    private String fotoPerfilUrl;
}
