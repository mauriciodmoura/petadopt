package com.example.petadopt.service.impl;

import com.example.petadopt.model.Animal;
import com.example.petadopt.repository.AnimalRepository;
import com.example.petadopt.service.AnimalService;

import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
