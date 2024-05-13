package com.example.ProjetFinal1.web;

import com.example.ProjetFinal1.entities.secretaire;
import com.example.ProjetFinal1.service.SecretaireService;
import com.example.ProjetFinal1.repositories.SecretaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecretaireController {
    @Autowired
    private SecretaireRepository secretaireRepository;
    @Autowired
    private SecretaireService secretaireService;

    @GetMapping("/secretaires")
    public String listSecretaires(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(name = "keyword", defaultValue = "") String keyword,Model model) {
        // Nombre d'éléments par page
        int pageSize = 10; // Vous pouvez ajuster cela selon vos besoins
        Page<secretaire> secretairePage;
        // Récupérer la page de secrétaires depuis la base de données
        if (keyword.isEmpty()) {
            secretairePage = secretaireRepository.findAll(PageRequest.of(page, pageSize));
        } else {
            // Sinon, effectuer une recherche par nom
            secretairePage = secretaireRepository.findByNomContains(keyword, PageRequest.of(page, pageSize));
        }

        // Envoyer les données à la vue Thymeleaf
        model.addAttribute("secretaires", secretairePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", secretairePage.getTotalPages());
        model.addAttribute("sec", new secretaire());

        return "secretaires";
    }

    @PutMapping("/archiveEmployee/{employeeId}")
    public ResponseEntity<String> archiveEmployee(@PathVariable("employeeId") String employeeId) {
        // Rechercher l'employé par son ID dans la base de données
        secretaire employee = secretaireRepository.findByLogin(employeeId);
        if (employee == null) {
            // Si l'employé n'est pas trouvé, retourner une erreur 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        // Archiver l'employé en définissant l'attribut archive sur true
        employee.setArchive(true);

        // Enregistrer les modifications dans la base de données
        secretaireRepository.save(employee);

        // Retourner une réponse OK avec un message de confirmation
        return ResponseEntity.ok("Employee archived successfully");
    }

    @PutMapping("/updateEmployee/{id}")
    @ResponseBody
    public String updateEmployee(@RequestBody secretaire updatedsecretaire) {
        updatedsecretaire.setRole("Secrétaire");
        // Call the service method to update employee details
        boolean success = secretaireService.update(updatedsecretaire);
        if (success) {
            return "Employee details updated successfully";
        } else {
            return "Failed to update employee details";
        }
    }

    @PostMapping("/save1")
    public String save(@ModelAttribute("sec") secretaire secretaire) {
        secretaireRepository.save(secretaire);
        return "redirect:/secretaires";
    }
}

