package com.example.TallerElectivaSupabase.services;

import com.example.TallerElectivaSupabase.entities.Supplier;
import com.example.TallerElectivaSupabase.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    public List<Supplier> findAllSupplier(){

        return supplierRepository.findAll();
    }

    public Supplier save(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public Supplier findById( Integer id ){
        Optional<Supplier> optionalSupplier = supplierRepository.findById( id );

        return optionalSupplier.isPresent() ? optionalSupplier.get() : null;
    }

    public void delete( Supplier supplier ){
        supplierRepository.delete( supplier );
    }
}
