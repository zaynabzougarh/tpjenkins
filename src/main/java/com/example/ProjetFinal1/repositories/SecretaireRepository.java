package com.example.ProjetFinal1.repositories;


import com.example.ProjetFinal1.entities.secretaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaireRepository extends JpaRepository<secretaire, String> {
    Page<secretaire> findByNomContains(String kw, Pageable pageable);
    secretaire findByLogin(String login);
}
