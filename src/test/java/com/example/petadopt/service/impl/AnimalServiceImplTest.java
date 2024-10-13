package com.example.petadopt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.petadopt.enums.Status;
import com.example.petadopt.model.Animal;
import com.example.petadopt.repository.AnimalRepository;

import jakarta.persistence.EntityNotFoundException;

public class AnimalServiceImplTest {

        @InjectMocks
    private AnimalServiceImpl animalService;

    @Mock
    private AnimalRepository animalRepository;

    private UUID animalId;
    private Animal animal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        animalId = UUID.randomUUID();
        animal = new Animal();
        animal.setId(animalId);
        animal.setName("Rex");
        animal.setDescription("Cão amigável");
        animal.setUrlImage("https://example.com/dog.jpg");
        animal.setCategory("Dog");
        animal.setBirthDate(LocalDate.of(2020, 5, 10));
        animal.setStatus(Status.DISPONIVEL);
    }

    @Test
    void testCreateAnimal() {
        when(animalRepository.save(animal)).thenReturn(animal);

        Animal createdAnimal = animalService.create(animal);

        assertEquals(animal, createdAnimal);
        verify(animalRepository, times(1)).save(animal);
    }

    @Test
    void testUpdateAnimal() {
        when(animalRepository.findById(animalId)).thenReturn(Optional.of(animal));
        when(animalRepository.save(animal)).thenReturn(animal);

        Animal updatedAnimal = animalService.update(animalId, animal);

        assertEquals(animal, updatedAnimal);
        verify(animalRepository, times(1)).findById(animalId);
        verify(animalRepository, times(1)).save(animal);
    }

    @Test
    void testFindByIdSuccess() {
        when(animalRepository.findById(animalId)).thenReturn(Optional.of(animal));

        Animal foundAnimal = animalService.findById(animalId);

        assertEquals(animal, foundAnimal);
        verify(animalRepository, times(1)).findById(animalId);
    }

    @Test
    void testFindByIdNotFound() {
        when(animalRepository.findById(animalId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> animalService.findById(animalId));
        verify(animalRepository, times(1)).findById(animalId);
    }

    @Test
    void testFindAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(animal);
        when(animalRepository.findAll()).thenReturn(animals);

        List<Animal> foundAnimals = animalService.findAll();

        assertEquals(1, foundAnimals.size());
        assertEquals(animal, foundAnimals.get(0));
        verify(animalRepository, times(1)).findAll();
    }

    @Test
    void testFindByCategory() {
        String category = "Dog";
        List<Animal> animals = new ArrayList<>();
        animals.add(animal);
        when(animalRepository.findByCategory(category)).thenReturn(animals);

        List<Animal> foundAnimals = animalService.findByCategory(category);

        assertEquals(1, foundAnimals.size());
        assertEquals(animal, foundAnimals.get(0));
        verify(animalRepository, times(1)).findByCategory(category);
    }

    @Test
    void testFindByStatus() {
        Status status = Status.DISPONIVEL;
        List<Animal> animals = new ArrayList<>();
        animals.add(animal);
        when(animalRepository.findByStatus(status)).thenReturn(animals);

        List<Animal> foundAnimals = animalService.findByStatus(status);

        assertEquals(1, foundAnimals.size());
        assertEquals(animal, foundAnimals.get(0));
        verify(animalRepository, times(1)).findByStatus(status);
    }


}
