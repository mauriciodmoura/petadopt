package com.example.petadopt.controller;

import com.example.petadopt.dto.AnimalCreateDTO;
import com.example.petadopt.dto.AnimalResponseDTO;
import com.example.petadopt.enums.Status;
import com.example.petadopt.mapper.AnimalMapper;
import com.example.petadopt.model.Animal;
import com.example.petadopt.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AnimalControllerTest {

    @InjectMocks
    private AnimalController animalController;

    @Mock
    private AnimalService animalService;

    @Mock
    private AnimalMapper animalMapper;

    private UUID animalId;
    private AnimalCreateDTO animalCreateDTO;
    private Animal animal;
    private AnimalResponseDTO animalResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        animalId = UUID.randomUUID();

        animalCreateDTO = new AnimalCreateDTO();
        animalCreateDTO.setName("Rex");
        animalCreateDTO.setDescription("Cão amigável");
        animalCreateDTO.setUrlImage("https://example.com/dog.jpg");
        animalCreateDTO.setCategory("Dog");
        animalCreateDTO.setBirthDate(LocalDate.of(2020, 5, 10));
        animalCreateDTO.setStatus(Status.DISPONIVEL);

        animal = new Animal();
        animal.setId(animalId);
        animal.setName(animalCreateDTO.getName());
        animal.setDescription(animalCreateDTO.getDescription());
        animal.setUrlImage(animalCreateDTO.getUrlImage());
        animal.setCategory(animalCreateDTO.getCategory());
        animal.setBirthDate(animalCreateDTO.getBirthDate());
        animal.setStatus(animalCreateDTO.getStatus());

        animalResponseDTO = new AnimalResponseDTO();
        animalResponseDTO.setId(animal.getId());
        animalResponseDTO.setName(animal.getName());
        animalResponseDTO.setDescription(animal.getDescription());
        animalResponseDTO.setUrlImage(animal.getUrlImage());
        animalResponseDTO.setCategory(animal.getCategory());
        animalResponseDTO.setBirthDate(animal.getBirthDate());
        animalResponseDTO.setStatus(animal.getStatus());
        animalResponseDTO.setAge(3);
    }


    @Test
    void testCreateAnimal() {
        when(animalMapper.toAnimal(animalCreateDTO)).thenReturn(animal);
        when(animalService.create(animal)).thenReturn(animal);
        when(animalMapper.toAnimalResponse(animal)).thenReturn(animalResponseDTO);

        ResponseEntity<AnimalResponseDTO> response = animalController.create(animalCreateDTO);

        assertEquals(ResponseEntity.ok(animalResponseDTO), response);
        assertEquals(animalResponseDTO.getName(), response.getBody().getName());

        verify(animalMapper, times(1)).toAnimal(animalCreateDTO);
        verify(animalService, times(1)).create(animal);
        verify(animalMapper, times(1)).toAnimalResponse(animal);
    }



    @Test
    void testGetById() {
        when(animalService.findById(animalId)).thenReturn(animal);
        when(animalMapper.toAnimalResponse(animal)).thenReturn(animalResponseDTO);

        ResponseEntity<AnimalResponseDTO> response = animalController.getById(animalId);

        assertEquals(ResponseEntity.ok(animalResponseDTO), response);
        assertEquals(animalResponseDTO.getName(), response.getBody().getName());

        verify(animalService, times(1)).findById(animalId);
        verify(animalMapper, times(1)).toAnimalResponse(animal);
    }

    @Test
    void testSearchByCategory() {
        String category = "Dog";

        List<Animal> animals = new ArrayList<>();
        animals.add(animal);

        List<AnimalResponseDTO> animalResponseDTOs = new ArrayList<>();
        animalResponseDTOs.add(animalResponseDTO);

        when(animalService.findByCategory(category)).thenReturn(animals);
        when(animalMapper.toAnimalResponse(animal)).thenReturn(animalResponseDTO);

        ResponseEntity<List<AnimalResponseDTO>> response = animalController.searchByCategory(category);

        assertEquals(ResponseEntity.ok(animalResponseDTOs), response);
        assertEquals(1, response.getBody().size());
        assertEquals(animalResponseDTO.getName(), response.getBody().get(0).getName());

        verify(animalService, times(1)).findByCategory(category);
        verify(animalMapper, times(1)).toAnimalResponse(animal);
    }

    @Test
    void testFindByStatus() {
        Status status = Status.DISPONIVEL;

        List<Animal> animals = new ArrayList<>();
        animals.add(animal);

        List<AnimalResponseDTO> animalResponseDTOs = new ArrayList<>();
        animalResponseDTOs.add(animalResponseDTO);

        when(animalService.findByStatus(status)).thenReturn(animals);
        when(animalMapper.toAnimalResponse(animal)).thenReturn(animalResponseDTO);

        ResponseEntity<List<AnimalResponseDTO>> response = animalController.findByStatus(status);

        assertEquals(ResponseEntity.ok(animalResponseDTOs), response);
        assertEquals(1, response.getBody().size());
        assertEquals(animalResponseDTO.getName(), response.getBody().get(0).getName());

        verify(animalService, times(1)).findByStatus(status);
        verify(animalMapper, times(1)).toAnimalResponse(animal);
    }
}