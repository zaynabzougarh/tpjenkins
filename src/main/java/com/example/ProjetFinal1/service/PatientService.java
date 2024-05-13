package com.example.ProjetFinal1.service;
import com.example.ProjetFinal1.entities.patientNonAuthetifie;
import com.example.ProjetFinal1.repositories.PatientNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientNRepository patientRepository;
    public boolean update(patientNonAuthetifie patient) {
        patientRepository.save(patient);
        return true;
    }
}
