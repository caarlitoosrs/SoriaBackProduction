package com.experienciassoria.repository;

import com.experienciassoria.model.Experiencia;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, UUID> {

    List<Experiencia> findByActivoTrue(Pageable pageable);
}

