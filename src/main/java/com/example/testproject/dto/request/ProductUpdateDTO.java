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
public class ProductUpdateDTO {
    @NotNull(message = "Id is mandatory")
    @Min(0)
    private Integer id;
    @NotBlank(message = "Describe is mandatory")
    private String description;
}
