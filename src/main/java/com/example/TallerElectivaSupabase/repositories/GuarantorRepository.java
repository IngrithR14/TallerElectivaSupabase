package com.example.TallerElectivaSupabase.repositories;

import com.example.TallerElectivaSupabase.entities.Guarantor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface GuarantorRepository extends JpaRepository<Guarantor,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Guarantor g SET g.nombre = :nombreN, g.apellido = :apellidoN, g.fecha_nacimiento = :fNacimiento,g.numeroMatricula = :numeroMatriculaN WHERE g.id = :id")
    public int updateGuarantorById(Integer id, String nombreN, String apellidoN, LocalDate fNacimiento,Integer numeroMatriculaN);
}
