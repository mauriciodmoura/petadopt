package com.example.petadopt.service.impl;

import com.example.petadopt.model.Animal;
import com.example.petadopt.repository.AnimalRepository;
import com.example.petadopt.service.AnimalService;
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

}
