package com.example.TallerElectivaSupabase.repositories;

import com.example.TallerElectivaSupabase.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
}
