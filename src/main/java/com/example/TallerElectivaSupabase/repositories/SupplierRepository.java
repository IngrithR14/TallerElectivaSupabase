package com.example.TallerElectivaSupabase.repositories;

import com.example.TallerElectivaSupabase.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
