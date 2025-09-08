package com.example.universidad.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int anio;

    private int cuatrimestre;

    @ManyToOne
    private Profesor profesor;

    @ManyToMany
    @JoinTable(
        name = "materia_correlativa",
        joinColumns = @JoinColumn(name = "materia_id"),
        inverseJoinColumns = @JoinColumn(name = "correlativa_id")
    )
    private List<Materia> correlatividades;

    @ManyToOne
    private Carrera carrera;

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public Profesor getProfesor() {

        return profesor;

    }

    public void setProfesor(Profesor profesor) {

        this.profesor = profesor;

    }

    public List<Materia> getCorrelatividades() {

        return correlatividades;

    }

    public void setCorrelatividades(List<Materia> correlatividades) {

        this.correlatividades = correlatividades;

    }

    public Carrera getCarrera() {

        return carrera;

    }

    public void setCarrera(Carrera carrera) {

        this.carrera = carrera;

    }

}
