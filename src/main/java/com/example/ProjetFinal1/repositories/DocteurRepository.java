package com.example.ProjetFinal1.repositories;

import com.example.ProjetFinal1.entities.Docteur;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocteurRepository extends JpaRepository<Docteur, Integer> {
    Page<Docteur> findByNomContains(String kw, Pageable pageable);
    @Query("SELECT d FROM Docteur d WHERE d.id_docteur = ?1")
    Docteur findById_docteur(int id_docteur);

   // Renommé en fonction du nom de l'attribut
}
