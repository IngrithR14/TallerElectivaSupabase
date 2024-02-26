package com.example.TallerElectivaSupabase.repositories;

import com.example.TallerElectivaSupabase.entities.Guarantor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuarantorRepository extends JpaRepository<Guarantor,Integer> {
}
