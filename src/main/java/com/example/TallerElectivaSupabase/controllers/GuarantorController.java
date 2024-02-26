package com.example.TallerElectivaSupabase.controllers;

import com.example.TallerElectivaSupabase.entities.Guarantor;
import com.example.TallerElectivaSupabase.entities.Buyer;
import com.example.TallerElectivaSupabase.responses.ResponseHandler;
import com.example.TallerElectivaSupabase.services.GuarantorService;
import com.example.TallerElectivaSupabase.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> save(@RequestBody Guarantor guarantor, @PathVariable Integer idComprador ){
        try{
            Buyer proper = properService.findById( idComprador );
            if( proper != null ){

                Guarantor result = guarantorService.save( guarantor, proper );

                return ResponseHandler.generateResponse("Succes",HttpStatus.CREATED, guarantor );
            }
            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
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

            return ResponseHandler.generateResponse("Success Author",HttpStatus.NOT_FOUND, null );

        }catch( Exception e ){

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
