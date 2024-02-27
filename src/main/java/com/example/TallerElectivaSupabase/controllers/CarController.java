package com.example.TallerElectivaSupabase.controllers;

import com.example.TallerElectivaSupabase.entities.Car;
import com.example.TallerElectivaSupabase.entities.Buyer;
import com.example.TallerElectivaSupabase.responses.ResponseHandler;
import com.example.TallerElectivaSupabase.services.CarService;
import com.example.TallerElectivaSupabase.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private BuyerService buyerService;
    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Car> result = carService.findAll();

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> save(@RequestBody Car car, @PathVariable Integer id ){
        try{
            Buyer proper = buyerService.findById( id );
            if( proper != null ){

                Car result = carService.save( car, proper);

                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, car );
            }
            return ResponseHandler.generateResponse("No se encontro comprador ",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
    @PutMapping("/{id}/car")
    public ResponseEntity<Object> updateCar(@PathVariable Integer id,
                                               @RequestParam String marcaN,
                                               @RequestParam String colorN,
                                               @RequestParam String matriculaN) {
        try {
            int updatedRowsCar = carService.updateCarById(id,marcaN,colorN,matriculaN);

            if (updatedRowsCar > 0) {
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, null);
            } else {
                return ResponseHandler.generateResponse("No se encontró el carro con el ID proporcionado", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id ){
        try{
            Car car = carService.findById( id );
            if( car != null ){

                carService.delete( car );

                return ResponseHandler.generateResponse("Succes",HttpStatus.ACCEPTED, car );
            }

            return ResponseHandler.generateResponse("No se encontro el carro",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
