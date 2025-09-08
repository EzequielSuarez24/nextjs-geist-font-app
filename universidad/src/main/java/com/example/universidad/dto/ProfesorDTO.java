package com.example.universidad.dto;

import jakarta.validation.constraints.NotBlank;

public class ProfesorDTO {
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

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

    public String getApellido() {

        return apellido;

    }

    public void setApellido(String apellido) {

        this.apellido = apellido;

    }

}
