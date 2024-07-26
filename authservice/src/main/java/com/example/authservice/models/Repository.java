package com.example.authservice.models;

import org.springframework.data.jpa.repository.JpaRepository;



@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Appuser, Long>{
    Appuser findByUsername(String username);
}
