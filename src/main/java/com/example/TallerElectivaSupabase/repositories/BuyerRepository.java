package com.example.TallerElectivaSupabase.repositories;

import com.example.TallerElectivaSupabase.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Integer> {
    @Query("SELECT a FROM Buyer a WHERE a.nombre LIKE CONCAT('%',:nombre,'%')")
    public List<Buyer> findByName(String nombre);
    @Modifying
    @Transactional
    @Query("UPDATE Buyer p SET p.nombre = :nombreN, p.apellido = :apellidoN, p.fecha_nacimiento = :fNacimiento WHERE p.id = :id")
    public int updateProperById(Integer id, String nombreN, String apellidoN, LocalDate fNacimiento);
}
