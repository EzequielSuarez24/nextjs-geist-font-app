package com.example.universidad.controller;

import com.example.universidad.dto.AsignaturaDTO;
import com.example.universidad.dto.AlumnoDTO;
import com.example.universidad.model.Estado;
import com.example.universidad.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> getAll() {
        return ResponseEntity.ok(alumnoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AlumnoDTO> create(@Valid @RequestBody AlumnoDTO dto) {
        return ResponseEntity.status(201).body(alumnoService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDTO> update(@PathVariable Long id, @Valid @RequestBody AlumnoDTO dto) {
        return ResponseEntity.ok(alumnoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{alumnoId}/asignatura/{asignaturaId}")
    public ResponseEntity<AsignaturaDTO> updateAsignaturaEstado(@PathVariable Long alumnoId, @PathVariable Long asignaturaId, @RequestParam Estado estado) {
        return ResponseEntity.ok(alumnoService.updateAsignaturaEstado(alumnoId, asignaturaId, estado));
    }
}
