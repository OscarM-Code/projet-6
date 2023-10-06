package com.openclassrooms.mddapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ThemeDto {
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    private String description;

}
