package com.example.petadopt.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

import com.example.petadopt.enums.Status;

@Data
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;
    
    private String name;

    private String description;

    private String urlImage;

    private String category;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    
    public int getAge() {
        return LocalDate.now().getYear() - this.birthDate.getYear();
    }


}