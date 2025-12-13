package com.experienciassoria.repository;

import com.experienciassoria.model.Comentario;
import com.experienciassoria.model.Experiencia;
import com.experienciassoria.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {
    
    List<Comentario> findByExperienciaAndUsuarioActivoTrueOrderByFechaDesc(Experiencia experiencia);

    List<Comentario> findByUsuarioOrderByFechaDesc(Usuario usuario);
}
