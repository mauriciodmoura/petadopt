package com.example.petadopt.service;

import java.util.UUID;

import com.example.petadopt.model.Animal;

public interface AnimalService {

    Animal create(Animal animal);

    Animal update(UUID id, Animal updatedAnimal);

    Animal findById(UUID id);

}
