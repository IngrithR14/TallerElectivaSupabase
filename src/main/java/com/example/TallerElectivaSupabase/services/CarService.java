package com.example.TallerElectivaSupabase.services;

import com.example.TallerElectivaSupabase.entities.Car;
import com.example.TallerElectivaSupabase.entities.Buyer;
import com.example.TallerElectivaSupabase.entities.Supplier;
import com.example.TallerElectivaSupabase.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;
    public List<Car> findAll(){

        return carRepository.findAll();
    }

    public Car save(Car car, Buyer buyer){
       car.setBuyer(buyer);

        return carRepository.save(car);
    }

    public Car findById( Integer id ){
        Optional<Car> optionalCar = carRepository.findById( id );

        return optionalCar.isPresent() ? optionalCar.get() : null;
    }

    public void delete( Car car ){
        carRepository.delete( car );
    }
}
