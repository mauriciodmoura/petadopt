package com.example.petadopt.service.impl;

import com.example.petadopt.dto.AnimalResponseDTO;
import com.example.petadopt.enums.Status;
import com.example.petadopt.model.Animal;
import com.example.petadopt.repository.AnimalRepository;
import com.example.petadopt.service.AnimalService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class AnimalServiceImpl implements AnimalService{

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal create(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal update(UUID id, Animal animal) {
        Animal existingAnimal = animalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado com o ID: " + id));

        existingAnimal.setName(animal.getName());
        existingAnimal.setDescription(animal.getDescription());
        existingAnimal.setUrlImage(animal.getUrlImage());
        existingAnimal.setCategory(animal.getCategory());
        existingAnimal.setBirthDate(animal.getBirthDate());
        existingAnimal.setStatus(animal.getStatus());

        return animalRepository.save(existingAnimal);
    }

    @Override
    public Animal findById(UUID id) {
        return animalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado com o ID: " + id));
    }

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public List<Animal> findByCategory(String category) {
        return animalRepository.findByCategory(category);
    }

    @Override
    public List<Animal> findByStatus(Status status) {
        return animalRepository.findByStatus(status);
    }

}
