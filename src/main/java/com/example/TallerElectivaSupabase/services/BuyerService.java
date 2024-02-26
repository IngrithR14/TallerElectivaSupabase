package com.example.TallerElectivaSupabase.services;

import com.example.TallerElectivaSupabase.entities.Car;
import com.example.TallerElectivaSupabase.entities.Buyer;
import com.example.TallerElectivaSupabase.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {
    @Autowired
    BuyerRepository properRepository;
    public List<Buyer> findAll(){

        return properRepository.findAll();
    }

    public Buyer findById(Integer id ){
        Optional<Buyer> optionalProper = properRepository.findById(id);

        return optionalProper.isPresent() ? optionalProper.get() : null;
    }

    public Buyer save(Buyer proper ){

        return properRepository.save( proper );
    }
    @Modifying
    @Transactional
    public int updateProperById(Integer id, String nombreN, String apellidoN, LocalDate fNacimiento) {
        return properRepository.updateProperById(id, nombreN, apellidoN,fNacimiento);
    }

    public List<Buyer> findByName(String nombre ){

        return properRepository.findByName( nombre );
    }

    public List<Car> getCars(Buyer buyer ){
        return buyer.getCars();
    }

    public void delete( Buyer buyer ){
       properRepository.delete( buyer );
    }
}
