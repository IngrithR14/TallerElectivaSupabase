package com.example.TallerElectivaSupabase.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false,length = 50,name = "nombreEmpresa")
    private String nombreEmpresa;
    @Column(nullable = false,length = 50,name = "contacto")
    private String contacto;
    @ManyToMany
    @JoinTable(
            name = "ProovedordeCarro",
            joinColumns = @JoinColumn(name = "suppliers_id"),
            inverseJoinColumns = @JoinColumn(name = "cars_id")
    )
    private List<Car> car;

    public Supplier() {
        car=new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }
}
