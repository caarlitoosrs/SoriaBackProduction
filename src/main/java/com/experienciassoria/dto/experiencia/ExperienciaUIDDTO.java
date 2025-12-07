package com.experienciassoria.dto.experiencia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ExperienciaUIDDTO {
    private UUID id;
    private String uid;
    private boolean activo;
    private Instant fechaGeneracion;
}
