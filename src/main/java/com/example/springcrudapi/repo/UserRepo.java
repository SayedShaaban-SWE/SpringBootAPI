package com.example.springcrudapi.repo;

import com.example.springcrudapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
