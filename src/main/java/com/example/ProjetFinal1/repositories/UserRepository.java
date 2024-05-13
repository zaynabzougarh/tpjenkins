package com.example.ProjetFinal1.repositories;

import com.example.ProjetFinal1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);
}
