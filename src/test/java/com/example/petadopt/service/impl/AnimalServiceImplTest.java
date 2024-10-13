package com.example.petadopt.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.petadopt.enums.Status;
import com.example.petadopt.model.Animal;
import com.example.petadopt.repository.AnimalRepository;

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


}
