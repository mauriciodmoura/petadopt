package com.example.petadopt.service.impl;

import com.example.petadopt.model.Animal;
import com.example.petadopt.repository.AnimalRepository;
import com.example.petadopt.service.AnimalService;

public class AnimalServiceImpl implements AnimalService{

    private AnimalRepository animalRepository;

    @Override
    public Animal create(Animal animal) {
        return animalRepository.save(animal);
    }

}
