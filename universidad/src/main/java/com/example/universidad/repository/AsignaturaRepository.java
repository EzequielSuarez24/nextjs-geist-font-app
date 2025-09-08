package com.example.universidad.repository;

import com.example.universidad.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
    Optional<Asignatura> findByAlumnoIdAndMateriaId(Long alumnoId, Long materiaId);
}
