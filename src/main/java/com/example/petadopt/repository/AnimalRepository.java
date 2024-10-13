package com.example.petadopt.repository;

import com.example.petadopt.model.Animal;
import com.example.petadopt.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByStatus(Status status);
}