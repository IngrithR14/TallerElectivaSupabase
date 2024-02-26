package com.example.TallerElectivaSupabase.controllers;

import com.example.TallerElectivaSupabase.entities.Car;
import com.example.TallerElectivaSupabase.entities.Buyer;
import com.example.TallerElectivaSupabase.entities.Supplier;
import com.example.TallerElectivaSupabase.responses.ResponseHandler;
import com.example.TallerElectivaSupabase.services.CarService;
import com.example.TallerElectivaSupabase.services.BuyerService;
import com.example.TallerElectivaSupabase.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private BuyerService properService;
    @Autowired
    private SupplierService supplierService;
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
    public ResponseEntity<Object> save(@RequestBody Car car, @PathVariable Integer id,@RequestParam Integer idS ){
        try{
            Buyer proper = properService.findById( id );
            Supplier supplier = supplierService.findById(idS);
            List<Supplier> supplierList=new ArrayList<>();
            supplierList.add(supplier);
            if( proper != null ){

                Car result = carService.save( car, proper ,supplierList);

                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, car );
            }
            return ResponseHandler.generateResponse("No se encontro comprador ",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
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

            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
