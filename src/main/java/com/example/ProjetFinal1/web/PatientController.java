package com.example.ProjetFinal1.web;

import com.example.ProjetFinal1.entities.Patient;
import com.example.ProjetFinal1.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PatientController {

    private final PatientRepo patientRepo;

    @Autowired
    public PatientController(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @GetMapping("/indexe")
    public String patients(Model model ) {
        List<Patient> patients = patientRepo.findAll();
        model.addAttribute("listPatients", patients);
        return "patients.html";
    }
}
