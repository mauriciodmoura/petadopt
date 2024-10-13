package com.example.petadopt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petadopt.model.Animal;
import com.example.petadopt.service.AnimalService;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<Animal> create(
        @RequestBody Animal animal
        ) {
        Animal newAnimal = animalService.create(animal);
        return ResponseEntity.ok(newAnimal);
    }

}
