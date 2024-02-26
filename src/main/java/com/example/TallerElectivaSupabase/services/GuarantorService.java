package com.example.TallerElectivaSupabase.services;

import com.example.TallerElectivaSupabase.entities.Guarantor;
import com.example.TallerElectivaSupabase.entities.Buyer;
import com.example.TallerElectivaSupabase.repositories.GuarantorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuarantorService {
    @Autowired
    GuarantorRepository guarantorRepository;
    public List<Guarantor> findAll(){

        return guarantorRepository.findAll();
    }

    public Guarantor save(Guarantor  guarantor, Buyer proper){
        guarantor.setBuyer(proper);
        return guarantorRepository.save(guarantor);
    }

    public Guarantor findById( Integer id ){
        Optional<Guarantor> optionalGuarantor = guarantorRepository.findById( id );

        return optionalGuarantor.isPresent() ? optionalGuarantor.get() : null;
    }

    public void delete( Guarantor guarantor ){
        guarantorRepository.delete( guarantor );
    }
}
