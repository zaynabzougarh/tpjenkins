package com.example.ProjetFinal1.service;

import com.example.ProjetFinal1.entities.User;
import com.example.ProjetFinal1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public void update(User user) {
        userRepository.save(user);
    }

}
