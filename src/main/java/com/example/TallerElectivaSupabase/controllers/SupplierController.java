package com.example.TallerElectivaSupabase.controllers;

import com.example.TallerElectivaSupabase.entities.Car;
import com.example.TallerElectivaSupabase.entities.Supplier;
import com.example.TallerElectivaSupabase.responses.ResponseHandler;
import com.example.TallerElectivaSupabase.services.CarService;
import com.example.TallerElectivaSupabase.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<Object> findAllSupplier(){
        try{
            List<Supplier> result = supplierService.findAllSupplier();

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Integer id ){
        try {
            Supplier supplier = supplierService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,supplier );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody Supplier supplier ){
        try{

                Supplier result = supplierService.save(supplier);

                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, supplier );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
    @PostMapping("/associatesupplier/{carId}/{supplierId}")
    public ResponseEntity<Object> associateCarWithSupplier(@PathVariable Integer carId, @PathVariable Integer supplierId) {
        Car car = carService.findById(carId);
        Supplier supplier = supplierService.findById(supplierId);

        if (car != null && supplier != null) {
            supplierService.associateCarWithSupplier(car, supplier);
            return ResponseEntity.ok("Car associated with Supplier successfully.");
        } else {
            return ResponseEntity.badRequest().body("Car or Supplier not found.");
        }
    }
    @PutMapping("/{id}/supplier")
    public ResponseEntity<Object> updateSupplier(@PathVariable Integer id,
                                               @RequestParam String nombreEmpresaN,
                                               @RequestParam String contactoN) {
        try {
            int updatedRowsSupplier = supplierService.updateSupplierById(id,nombreEmpresaN,contactoN);

            if (updatedRowsSupplier > 0) {
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, null);
            } else {
                return ResponseHandler.generateResponse("No se encontró el proveedor con el ID proporcionado", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id ){
        try{
            Supplier supplier = supplierService.findById( id );
            if( supplier != null ){

                supplierService.delete( supplier );

                return ResponseHandler.generateResponse("Succes",HttpStatus.ACCEPTED, supplier );
            }

            return ResponseHandler.generateResponse("No se encontro proveedor",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
