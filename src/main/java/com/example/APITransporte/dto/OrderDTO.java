package com.example.APITransporte.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.APITransporte.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record OrderDTO(
        UUID id,

        @NotNull(message = "Estatus no puede ser nulo")
        Status status,

        @NotBlank(message = "Origen no puede estar vacío")
        @Size(min = 3, max = 100, message = "Origen debe tener entre 3 y 100 caracteres")
        String origin,

        @NotBlank(message = "Destino no puede estar vacío.")
        @Size(min = 3, max = 100, message = "Destino debe tener entre 3 y 100 caracteres")
        String destination,

        @NotNull(message = "La fecha de creación es obligatoria.")
        @PastOrPresent(message = "La fecha de creación no puede ser una fecha futura")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,

        @PastOrPresent(message = "La fecha de actualización no puede ser una fecha futura")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt
) {}
