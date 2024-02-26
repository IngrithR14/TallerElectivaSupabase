package com.example.TallerElectivaSupabase.controllers;

import com.example.TallerElectivaSupabase.entities.Supplier;
import com.example.TallerElectivaSupabase.responses.ResponseHandler;
import com.example.TallerElectivaSupabase.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id ){
        try{
            Supplier supplier = supplierService.findById( id );
            if( supplier != null ){

                supplierService.delete( supplier );

                return ResponseHandler.generateResponse("Succes",HttpStatus.ACCEPTED, supplier );
            }

            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
