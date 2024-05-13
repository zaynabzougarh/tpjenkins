package com.example.ProjetFinal1.web;


import com.example.ProjetFinal1.entities.patientNonAuthetifie;
import com.example.ProjetFinal1.service.PatientService;
import com.example.ProjetFinal1.repositories.PatientNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientNController {
    @Autowired
    private PatientNRepository patientRepository;

    @GetMapping("/patient")
    public String listPatients(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(name = "keyword", defaultValue = "") String keyword,
                               Model model) {
        int pageSize = 10;
        Page<patientNonAuthetifie> patientPage;

        if (keyword.isEmpty()) {
            patientPage = patientRepository.findAll(PageRequest.of(page, pageSize));
        } else {
            // Utiliser la méthode findByNomContains si le mot-clé n'est pas vide
            patientPage = patientRepository.findByNomContains(keyword, PageRequest.of(page, pageSize));
        }

        model.addAttribute("patients", patientPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", patientPage.getTotalPages());
        model.addAttribute("pat", new patientNonAuthetifie());
        return "patient";
    }
    @PostMapping("/save2")
    public String save(@ModelAttribute("pat") patientNonAuthetifie patient) {
        patientRepository.save(patient);
        return "redirect:/patient";
    }

    @PutMapping("/archiveEmployeepat/{employeeId}")
    public ResponseEntity<String> archiveEmployee(@PathVariable("employeeId") String employeeId) {
        // Rechercher l'employé par son ID dans la base de données
        patientNonAuthetifie employee = patientRepository.findByCIN(employeeId);
        if (employee == null) {
            // Si l'employé n'est pas trouvé, retourner une erreur 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // Archiver l'employé en définissant l'attribut archive sur true
        employee.setArchive(true);

        // Enregistrer les modifications dans la base de données
        patientRepository.save(employee);

        // Retourner une réponse OK avec un message de confirmation
        return ResponseEntity.ok("Employee archived successfully");
    }


    @Autowired
    private PatientService patientService;

    @PutMapping("/updateEmployeepat/{id}")
    @ResponseBody
    public String updateEmployee(@RequestBody patientNonAuthetifie updatedPatient) {
        // Call the service method to update patient details
        boolean success = patientService.update(updatedPatient);
        if (success) {
            return "Patient details updated successfully";
        } else {
            return "Failed to update patient details";
        }
    }
}
