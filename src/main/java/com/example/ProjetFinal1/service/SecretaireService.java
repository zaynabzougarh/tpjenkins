package com.example.ProjetFinal1.service;

import com.example.ProjetFinal1.entities.User;
import com.example.ProjetFinal1.entities.secretaire;
import com.example.ProjetFinal1.repositories.SecretaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretaireService {
    @Autowired
    private SecretaireRepository secretaireRepository;
    public boolean update(secretaire secretairee) {
        secretaireRepository.save(secretairee);
        return true;
    }
}
