package com.example.petadopt.mapper;

import org.springframework.stereotype.Component;

import com.example.petadopt.dto.AnimalCreateDTO;
import com.example.petadopt.dto.AnimalResponseDTO;
import com.example.petadopt.dto.AnimalUpdateDTO;
import com.example.petadopt.model.Animal;

@Component
public class AnimalMapper {

    public Animal toAnimal(
        AnimalCreateDTO dto
    ) {
        Animal animal = new Animal();
        animal.setName(dto.getName());
        animal.setDescription(dto.getDescription());
        animal.setUrlImage(dto.getUrlImage());
        animal.setCategory(dto.getCategory());
        animal.setBirthDate(dto.getBirthDate());
        animal.setStatus(dto.getStatus());
        return animal;
    }

    public AnimalResponseDTO toAnimalResponse(
        Animal animal
    ) {
        AnimalResponseDTO dto = new AnimalResponseDTO();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setDescription(animal.getDescription());
        dto.setUrlImage(animal.getUrlImage());
        dto.setCategory(animal.getCategory());
        dto.setBirthDate(animal.getBirthDate());
        dto.setStatus(animal.getStatus());
        dto.setAge(animal.getAge());
        return dto;
    }

    public void toAnimalUpdate(
        AnimalUpdateDTO dto, 
        Animal animal
    ) {
        animal.setName(dto.getName());
        animal.setDescription(dto.getDescription());
        animal.setUrlImage(dto.getUrlImage());
        animal.setCategory(dto.getCategory());
        animal.setBirthDate(dto.getBirthDate());
        animal.setStatus(dto.getStatus());
    }
}
