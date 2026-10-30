package com.example.APITransporte.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionOrdenDTO {

    Integer id;
    @NotNull(message = "El ID de la orden es obligatorio")
    private UUID idOrder;

    @NotNull(message = "El ID del conductor es obligatorio")
    private UUID idDriver;
    String rutaAsignacionPdf;
    String rutaImagen;

}
