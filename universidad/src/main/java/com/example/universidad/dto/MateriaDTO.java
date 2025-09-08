package com.example.universidad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class MateriaDTO {
    private Long id;

    @NotBlank
    private String nombre;

    @NotNull
    private Integer anio;

    @NotNull
    private Integer cuatrimestre;

    private Long profesorId;

    private List<Long> correlatividadesIds;

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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Integer cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public List<Long> getCorrelatividadesIds() {
        return correlatividadesIds;
    }

    public void setCorrelatividadesIds(List<Long> correlatividadesIds) {
        this.correlatividadesIds = correlatividadesIds;
    }
}
