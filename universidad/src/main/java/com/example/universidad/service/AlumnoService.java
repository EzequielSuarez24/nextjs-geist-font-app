package com.example.universidad.service;

import com.example.universidad.dto.AsignaturaDTO;
import com.example.universidad.dto.AlumnoDTO;
import com.example.universidad.exception.BadRequestException;
import com.example.universidad.exception.NotFoundException;
import com.example.universidad.model.Asignatura;
import com.example.universidad.model.Alumno;
import com.example.universidad.model.Estado;
import com.example.universidad.model.Materia;
import com.example.universidad.repository.AsignaturaRepository;
import com.example.universidad.repository.AlumnoRepository;
import com.example.universidad.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoService {
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<AlumnoDTO> getAll() {
        return alumnoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AlumnoDTO getById(Long id) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(() -> new NotFoundException("Alumno no encontrado"));
        return toDTO(alumno);
    }

    public AlumnoDTO create(AlumnoDTO dto) {
        Alumno alumno = new Alumno();
        alumno.setNombre(dto.getNombre());
        alumno.setApellido(dto.getApellido());
        // Generate asignaturas for all materias
        List<Materia> materias = materiaRepository.findAll();
        List<Asignatura> asignaturas = new ArrayList<>();
        for (Materia materia : materias) {
            Asignatura asignatura = new Asignatura();
            asignatura.setEstado(Estado.LIBRE);
            asignatura.setAlumno(alumno);
            asignatura.setMateria(materia);
            asignaturas.add(asignatura);
        }
        alumno.setAsignaturas(asignaturas);
        Alumno saved = alumnoRepository.save(alumno);
        return toDTO(saved);
    }

    public AlumnoDTO update(Long id, AlumnoDTO dto) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(() -> new NotFoundException("Alumno no encontrado"));
        alumno.setNombre(dto.getNombre());
        alumno.setApellido(dto.getApellido());
        Alumno saved = alumnoRepository.save(alumno);
        return toDTO(saved);
    }

    public void delete(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new NotFoundException("Alumno no encontrado");
        }
        alumnoRepository.deleteById(id);
    }

    public AsignaturaDTO updateAsignaturaEstado(Long alumnoId, Long asignaturaId, Estado nuevoEstado) {
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId).orElseThrow(() -> new NotFoundException("Asignatura no encontrada"));
        if (!asignatura.getAlumno().getId().equals(alumnoId)) {
            throw new BadRequestException("Asignatura no pertenece al alumno");
        }
        // Validate business rules
        Estado actual = asignatura.getEstado();
        Materia materia = asignatura.getMateria();
        if (nuevoEstado == Estado.CURSANDO) {
            // Check correlativas
            for (Materia corr : materia.getCorrelatividades()) {
                Asignatura corrAsig = asignaturaRepository.findByAlumnoIdAndMateriaId(alumnoId, corr.getId());
                if (corrAsig == null || corrAsig.getEstado() != Estado.APROBADA) {
                    throw new BadRequestException("No se pueden cursar correlativas no aprobadas");
                }
            }
        } else if (nuevoEstado == Estado.APROBADA) {
            if (actual != Estado.CURSANDO) {
                throw new BadRequestException("Solo se puede aprobar si se está cursando");
            }
            // Check correlativas
            for (Materia corr : materia.getCorrelatividades()) {
                Asignatura corrAsig = asignaturaRepository.findByAlumnoIdAndMateriaId(alumnoId, corr.getId());
                if (corrAsig == null || corrAsig.getEstado() != Estado.APROBADA) {
                    throw new BadRequestException("No se pueden aprobar sin correlativas aprobadas");
                }
            }
        } else if (nuevoEstado == Estado.LIBRE) {
            if (actual != Estado.CURSANDO) {
                throw new BadRequestException("Solo se puede liberar si se está cursando");
            }
        }
        asignatura.setEstado(nuevoEstado);
        Asignatura saved = asignaturaRepository.save(asignatura);
        return toDTO(saved);
    }

    private AlumnoDTO toDTO(Alumno alumno) {
        AlumnoDTO dto = new AlumnoDTO();
        dto.setId(alumno.getId());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());
        return dto;
    }

    private AsignaturaDTO toDTO(Asignatura asignatura) {
        AsignaturaDTO dto = new AsignaturaDTO();
        dto.setId(asignatura.getId());
        dto.setEstado(asignatura.getEstado());
        dto.setAlumnoId(asignatura.getAlumno().getId());
        dto.setMateriaId(asignatura.getMateria().getId());
        return dto;
    }
}
