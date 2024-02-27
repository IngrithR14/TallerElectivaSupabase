package com.example.TallerElectivaSupabase.services;

import com.example.TallerElectivaSupabase.entities.Car;
import com.example.TallerElectivaSupabase.entities.Supplier;
import com.example.TallerElectivaSupabase.repositories.CarRepository;
import com.example.TallerElectivaSupabase.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CarRepository carRepository;
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
    public void associateCarWithSupplier(Car car, Supplier supplier) {
        car.getSupplier().add(supplier);
        supplier.getCar().add(car);
        carRepository.save(car);
        supplierRepository.save(supplier);
    }
    @Modifying
    @Transactional
    public int updateSupplierById(Integer id, String nombreEmpresaN, String contactoN) {
        return supplierRepository.updateSupplierById(id,nombreEmpresaN,contactoN);
    }
    public void delete( Supplier supplier ){
        supplierRepository.delete( supplier );
    }
}
