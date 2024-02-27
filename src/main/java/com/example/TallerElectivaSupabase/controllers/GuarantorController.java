package com.example.TallerElectivaSupabase.controllers;

import com.example.TallerElectivaSupabase.entities.Guarantor;
import com.example.TallerElectivaSupabase.entities.Buyer;
import com.example.TallerElectivaSupabase.responses.ResponseHandler;
import com.example.TallerElectivaSupabase.services.GuarantorService;
import com.example.TallerElectivaSupabase.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("guarantor")
public class GuarantorController {
    @Autowired
    GuarantorService guarantorService;
    @Autowired
    private BuyerService properService;
    @GetMapping
    public ResponseEntity<Object> findAll(){
        try{
            List<Guarantor> result = guarantorService.findAll();

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById( @PathVariable Integer id ){
        try {
            Guarantor guarantor = guarantorService.findById( id );

            return  ResponseHandler.generateResponse("Success",HttpStatus.OK,guarantor );
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> save(@RequestBody Guarantor guarantor, @PathVariable Integer id ){
        try{
            Buyer proper = properService.findById( id );
            if( proper != null ){

                Guarantor result = guarantorService.save( guarantor, proper );

                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, guarantor );
            }
            return ResponseHandler.generateResponse("No encuentro propietario",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
    @PutMapping("/{id}/guarantor")
    public ResponseEntity<Object> updateGuarantor(@PathVariable Integer id,
                                               @RequestParam String nombreN,
                                               @RequestParam String apellidoN,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fNacimiento,
                                               @RequestParam Integer numeroMatriculaN) {
        try {
            int updatedRowsGuarantor = guarantorService.updateGuarantorById(id,nombreN,apellidoN,fNacimiento,numeroMatriculaN);

            if (updatedRowsGuarantor > 0) {
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, null);
            } else {
                return ResponseHandler.generateResponse("No se encontró el fiador con el ID proporcionado", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id ){
        try{
            Guarantor guarantor = guarantorService.findById( id );
            if( guarantor != null ){

                guarantorService.delete( guarantor );

                return ResponseHandler.generateResponse("Succes",HttpStatus.ACCEPTED, guarantor );
            }

            return ResponseHandler.generateResponse("No se encontro Fiador",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
