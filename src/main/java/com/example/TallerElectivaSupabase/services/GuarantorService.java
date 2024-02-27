package com.example.TallerElectivaSupabase.services;

import com.example.TallerElectivaSupabase.entities.Guarantor;
import com.example.TallerElectivaSupabase.entities.Buyer;
import com.example.TallerElectivaSupabase.repositories.GuarantorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    @Modifying
    @Transactional
    public int updateGuarantorById(Integer id, String nombreN, String apellidoN, LocalDate fNacimiento,Integer numeroMatriculaN) {
        return guarantorRepository.updateGuarantorById(id, nombreN, apellidoN,fNacimiento,numeroMatriculaN);
    }

    public void delete( Guarantor guarantor ){
        guarantorRepository.delete( guarantor );
    }
}
