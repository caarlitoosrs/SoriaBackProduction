package com.experienciassoria.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String nombre;
    private String email;
    private String password;
    private String role;
    private String fotoPerfilUrl;
    private Boolean activo;
}
