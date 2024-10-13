package com.example.petadopt.model;

import com.example.petadopt.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {

    private Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Animal();
        animal.setId(UUID.randomUUID());
        animal.setName("Rex");
        animal.setDescription("Cão amigável");
        animal.setUrlImage("https://example.com/dog.jpg");
        animal.setCategory("Dog");
        animal.setBirthDate(LocalDate.of(2020, 5, 10));
        animal.setStatus(Status.DISPONIVEL);
    }

    @Test
    void testAnimalAttributes() {
        assertNotNull(animal.getId());
        assertEquals("Rex", animal.getName());
        assertEquals("Cão amigável", animal.getDescription());
        assertEquals("https://example.com/dog.jpg", animal.getUrlImage());
        assertEquals("Dog", animal.getCategory());
        assertEquals(LocalDate.of(2020, 5, 10), animal.getBirthDate());
        assertEquals(Status.DISPONIVEL, animal.getStatus());
    }

    @Test
    void testGetAge() {
        int expectedAge = LocalDate.now().getYear() - 2020;
        assertEquals(expectedAge, animal.getAge());
    }

    @Test
    void testSettersAndGetters() {
        UUID newId = UUID.randomUUID();
        animal.setId(newId);
        animal.setName("Max");
        animal.setDescription("Cão guardião");
        animal.setUrlImage("https://example.com/dog2.jpg");
        animal.setCategory("Guard Dog");
        animal.setBirthDate(LocalDate.of(2018, 1, 1));
        animal.setStatus(Status.ADOTADO);

        assertEquals(newId, animal.getId());
        assertEquals("Max", animal.getName());
        assertEquals("Cão guardião", animal.getDescription());
        assertEquals("https://example.com/dog2.jpg", animal.getUrlImage());
        assertEquals("Guard Dog", animal.getCategory());
        assertEquals(LocalDate.of(2018, 1, 1), animal.getBirthDate());
        assertEquals(Status.ADOTADO, animal.getStatus());
    }
}