package com.example.ProjetFinal1.web;


import com.example.ProjetFinal1.entities.User;
import com.example.ProjetFinal1.repositories.UserRepository;
import com.example.ProjetFinal1.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String userProfile(Model model, HttpSession session) {
        // Récupérer l'utilisateur à partir de la session
        User user = (User) session.getAttribute("user");
        // Récupérer l'utilisateur depuis la base de données pour obtenir les données actualisées
        User currentUser = userRepository.findByLogin(user.getLogin());
        model.addAttribute("user", currentUser);
        return "profile";
    }
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/profile";
    }
}


