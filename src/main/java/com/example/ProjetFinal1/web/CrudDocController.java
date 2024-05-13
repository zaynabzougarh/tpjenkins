package com.example.ProjetFinal1.web;

import com.example.ProjetFinal1.entities.Docteur;

import com.example.ProjetFinal1.entities.secretaire;
import com.example.ProjetFinal1.service.Docteurservice;
import com.example.ProjetFinal1.repositories.DocteurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CrudDocController {

    @Autowired
    private DocteurRepository docteurRepository;

    @GetMapping("/docteur")
    public String listDocteurs(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(name = "keyword", defaultValue = "") String keyword, Model model) {
        // Nombre d'éléments par page
        int pageSize = 10; // Vous pouvez ajuster cela selon vos besoins
        Page<Docteur> docteurPage;
        // Récupérer la page de docteurs depuis la base de données
        if (keyword.isEmpty()) {
        docteurPage = docteurRepository.findAll(PageRequest.of(page, pageSize));
        } else {
            docteurPage = docteurRepository.findByNomContains(keyword, PageRequest.of(page, pageSize));
        }
        // Envoyer les données à la vue Thymeleaf
        model.addAttribute("docteurs", docteurPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", docteurPage.getTotalPages());
        model.addAttribute("doc", new Docteur());
        return "docteur";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("doc") Docteur docteur) {
        docteurRepository.save(docteur);
        return "redirect:/docteur";
    }


    @PutMapping("/archiveEmployeedoc/{employeeId}")
    public ResponseEntity<String> archiveEmployee(@PathVariable("employeeId") Integer employeeId) {
        // Rechercher l'employé par son ID dans la base de données
        Docteur employee = docteurRepository.findById_docteur(employeeId);
        if (employee == null) {
            // Si l'employé n'est pas trouvé, retourner une erreur 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // Archiver l'employé en définissant l'attribut archive sur true
        employee.setArchive(true);

        // Enregistrer les modifications dans la base de données
        docteurRepository.save(employee);

        // Retourner une réponse OK avec un message de confirmation
        return ResponseEntity.ok("Employee archived successfully");
    }
    @Autowired
    private Docteurservice docteurService;

    @PutMapping("/updateEmployeedoc/{id}") // Mettez à jour le mapping pour inclure l'ID de l'employé
    @ResponseBody
    public String updateEmployee(@RequestBody Docteur updatedDocteur, @PathVariable("id") Integer id) { // Ajoutez une variable de chemin pour l'ID de l'employé
        updatedDocteur.setId_docteur(id); // Définir l'ID de l'employé à partir du chemin

        boolean success = docteurService.update(updatedDocteur); // Utilisation du service pour mettre à jour le docteur
        if (success) {
            return "Employee details updated successfully";
        } else {
            return "Failed to update employee details";
        }
    }

}
