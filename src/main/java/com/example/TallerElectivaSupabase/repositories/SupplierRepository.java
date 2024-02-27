package com.example.TallerElectivaSupabase.repositories;

import com.example.TallerElectivaSupabase.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Supplier s SET s.nombreEmpresa = :nombreEmpresaN, s.contacto = :contactoN WHERE s.id = :id")
    public int updateSupplierById(Integer id, String nombreEmpresaN, String contactoN);
}
