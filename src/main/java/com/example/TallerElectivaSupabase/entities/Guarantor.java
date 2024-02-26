package com.example.TallerElectivaSupabase.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "guarantors")
public class Guarantor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false,length = 50,name = "numeroMatricula")
    private Integer numeroMatricula;
    @Column(nullable = false,length = 50,name = "nombre")
    private String nombre;
    @Column(nullable = false,length = 50,name = "apellido")
    private String apellido;
    @Column(nullable = false)
    private LocalDate fecha_nacimiento;
    @OneToOne
    @JoinColumn(name = "buyer", unique = true)
    private Buyer buyer;

    public Guarantor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(Integer numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
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

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

}
