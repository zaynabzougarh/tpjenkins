package com.example.ProjetFinal1.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class rendez_Vous implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_RDV;
    private Date date_RDV;
    @Temporal(TemporalType.TIME)
    private Time heure_consultation;
    private String statut_RDV;
    @ManyToOne
    @JoinColumn(name="CIN")
    private patientNonAuthetifie patientNA;
    @ManyToOne
    @JoinColumn(name="id_docteur")
    private Docteur docteur;

}
