package com.example.petadopt.dto;

import java.time.LocalDate;

import com.example.petadopt.enums.Status;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AnimalCreateDTO {

    @Schema(
        description = "Nome do animal", 
        example = "Rex")
    private String name;

    @Schema(
        description = "Descrição do animal", 
        example = "Cão amigável")
    private String description;

    @Schema(
        description = "URL da imagem do animal", 
        example = "https://example.com/dog.jpg")
    private String urlImage;

    @Schema(
        description = "Categoria do animal", 
        example = "Dog")
    private String category;

    @Schema(
        description = "Data de nascimento", 
        example = "2020-05-15")
    private LocalDate birthDate;

    @Schema(
        description = "Status do animal", 
        example = "DISPONIVEL")
    private Status status;

}
