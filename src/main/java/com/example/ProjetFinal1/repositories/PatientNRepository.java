package com.example.ProjetFinal1.repositories;


import com.example.ProjetFinal1.entities.patientNonAuthetifie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientNRepository extends JpaRepository<patientNonAuthetifie, String> {
    Page<patientNonAuthetifie> findByNomContains(String kw, Pageable pageable);

    patientNonAuthetifie findByCIN(String CIN);
}
