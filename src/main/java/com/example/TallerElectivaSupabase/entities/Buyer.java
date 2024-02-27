package com.example.TallerElectivaSupabase.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false,length = 50,name = "nombre")
    private String nombre;
    @Column(nullable = false,length = 50,name = "apellido")
    private String apellido;
    @Column(nullable = false)
    private LocalDate fecha_nacimiento;
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Car> cars;
    @OneToOne(mappedBy = "buyer", cascade = CascadeType.REMOVE)
    private Guarantor fiador;

    public Buyer() {
        cars=new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Guarantor getFiador() {
        return fiador;
    }

    public void setFiador(Guarantor fiador) {
        this.fiador = fiador;
    }

}
