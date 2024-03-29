package com.example.TallerElectivaSupabase.controllers;

import com.example.TallerElectivaSupabase.entities.Car;
import com.example.TallerElectivaSupabase.entities.Buyer;
import com.example.TallerElectivaSupabase.responses.ResponseHandler;
import com.example.TallerElectivaSupabase.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("proper")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){

        try {

            List<Buyer> result = buyerService.findAll();

            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Integer id ){
        try {
            Buyer buyer = buyerService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,buyer );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Buyer proper ){
        try {
            Buyer result = buyerService.save( proper );

            return  ResponseHandler.generateResponse("Success",HttpStatus.CREATED,result);

        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
    @PutMapping("/{id}/proper")
    public ResponseEntity<Object> updateProper(@PathVariable Integer id,
                                               @RequestParam String nombreN,
                                               @RequestParam String apellidoN,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fNacimiento) {
        try {
            int updatedRows = buyerService.updateProperById(id,nombreN, apellidoN, fNacimiento);

            if (updatedRows > 0) {
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, null);
            } else {
                return ResponseHandler.generateResponse("No se encontró el comprador con el ID proporcionado", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Object> findByName(@PathVariable  String nombre){
        try {
            List<Buyer> result = buyerService.findByName( nombre );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,result );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Object> getBooks(@PathVariable Integer id){
        try{
            Buyer buyer = buyerService.findById( id );
            if( buyer != null ){

                List<Car> result = buyerService.getCars(buyer);

                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, result );
            }

            return ResponseHandler.generateResponse("No se encontro el comprador",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id ){
        try{
            Buyer proper = buyerService.findById( id );
            if( proper != null ){

                buyerService.delete( proper );

                return ResponseHandler.generateResponse("Succes",HttpStatus.ACCEPTED, proper );
            }

            return ResponseHandler.generateResponse("No se encontro comprador",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}

