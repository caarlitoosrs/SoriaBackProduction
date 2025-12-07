package com.experienciassoria.dto.comentario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarComentarioRequest {
    private String texto;
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
}
