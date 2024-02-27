package com.example.TallerElectivaSupabase.repositories;

import com.example.TallerElectivaSupabase.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Car c SET c.marca = :marcaN, c.color = :colorN,c.matricula = :matriculaN WHERE c.id = :id")
    public int updateCarById(Integer id, String marcaN, String colorN,String matriculaN);
}
