package com.experienciassoria.repository;

import com.experienciassoria.model.RegistroExperiencia;
import com.experienciassoria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegistroExperienciaRepository extends JpaRepository<RegistroExperiencia, UUID> {

    
    List<RegistroExperiencia> findByUsuario(Usuario usuario);

    
    boolean existsByUsuario_IdAndExperiencia_Id(UUID usuarioId, UUID experienciaId);

    
    Optional<RegistroExperiencia> findByUsuario_IdAndExperiencia_Id(UUID usuarioId, UUID experienciaId);
}
