package com.example.petadopt.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.petadopt.dto.AnimalCreateDTO;
import com.example.petadopt.dto.AnimalResponseDTO;
import com.example.petadopt.dto.AnimalUpdateDTO;
import com.example.petadopt.mapper.AnimalMapper;
import com.example.petadopt.model.Animal;
import com.example.petadopt.service.AnimalService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;

    private final AnimalMapper animalMapper;

    public AnimalController(
        AnimalService animalService,
        AnimalMapper animalMapper
    ) {
        this.animalService = animalService;
        this.animalMapper = animalMapper;
    }

    @PostMapping
    @Operation(summary = "Cria um novo animal")
    public ResponseEntity<AnimalResponseDTO> create(
        @RequestBody AnimalCreateDTO animalCreateDTO
    ) {
        Animal newAnimal = animalService.create(animalMapper.toAnimal(animalCreateDTO));
        return ResponseEntity.ok(animalMapper.toAnimalResponse(newAnimal));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um animal existente")
    public ResponseEntity<AnimalResponseDTO> update(
        @PathVariable UUID id,
        @RequestBody AnimalUpdateDTO animalUpdateDTO
    ) {
        Animal existingAnimal = animalService.findById(id);
        animalMapper.toAnimalUpdate(animalUpdateDTO, existingAnimal);
        Animal updatedAnimal = animalService.update(id, existingAnimal);
        return ResponseEntity.ok(animalMapper.toAnimalResponse(updatedAnimal));
    }

    @GetMapping
    @Operation(summary = "Busca todos os animais")
    public ResponseEntity<List<AnimalResponseDTO>> getAll() {
        List<Animal> animals = animalService.findAll();
        List<AnimalResponseDTO> animalDTOs = animals.stream()
            .map(animalMapper::toAnimalResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(animalDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um animal pelo ID")
    public ResponseEntity<AnimalResponseDTO> getById(
        @PathVariable UUID id
    ) {
        Animal animal = animalService.findById(id);
        return ResponseEntity.ok(animalMapper.toAnimalResponse(animal));
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Busca animais por categoria")
    public ResponseEntity<List<AnimalResponseDTO>> searchByCategory(
        @PathVariable String category
    ) {
        List<Animal> animals = animalService.findByCategory(category);
        List<AnimalResponseDTO> animalDTOs = animals.stream()
            .map(animalMapper::toAnimalResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(animalDTOs);
    }

}
