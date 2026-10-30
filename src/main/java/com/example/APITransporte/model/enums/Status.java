package com.example.APITransporte.model.enums;

import java.util.List;

public enum Status {

    CREATED,
    IN_TRANSIT,
    DELIVERED,
    CANCELLED;


    public List<Status> validaEstados() {
        return switch (this) {
            case CREATED -> List.of(IN_TRANSIT, CANCELLED);
            case IN_TRANSIT -> List.of(DELIVERED, CANCELLED);
            case DELIVERED, CANCELLED -> List.of(); // Estados finales, no permiten más cambios
        };
    }

    // Método utilitario para validar el cambio
    public boolean puedeCambiarEstadoA(Status nextStatus) {
        System.err.println("nextStatus:: "+nextStatus);
        return this.validaEstados().contains(nextStatus);
    }

}
