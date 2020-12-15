package com.example.testproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddDTO {
    @NotNull(message = "Price is mandatory")
    @Min(0)
    private Double price;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Describe is mandatory")
    private String description;
}

