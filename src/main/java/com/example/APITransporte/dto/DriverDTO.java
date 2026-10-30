package com.example.APITransporte.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DriverDTO(

        UUID id,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        String name,

        @NotBlank(message = "El número de licencia es obligatorio")
        @Size(min = 5, max = 20, message = "La licencia debe tener entre 5 y 20 caracteres")
        String licenseNumber,

        @NotNull(message = "El estado activo es obligatorio")
        Boolean active
) {}