package com.example.ProjetFinal1.service;

import com.example.ProjetFinal1.entities.Docteur;
import com.example.ProjetFinal1.repositories.DocteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Déclaration du service comme un bean Spring
public class Docteurservice {

    @Autowired
    private DocteurRepository docteurRepository;

    public boolean update(Docteur docteur) {
        docteurRepository.save(docteur);
        return true;
    }
}
