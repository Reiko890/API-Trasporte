package com.example.APITransporte.repository;

import com.example.APITransporte.model.entity.OrderEntity;
import com.example.APITransporte.model.enums.Status;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;

public class OrderSpecifications {

    public static Specification<OrderEntity> conStatus(Status status) {
        return (root, query, cb) -> (status != null) ? cb.equal(root.get("status"), status) : null;
    }

    public static Specification<OrderEntity> conOrigin(String origin) {
        return (root, query, cb) -> (origin != null && !origin.isBlank()) ? cb.equal(root.get("origin"), origin) : null;
    }

    public static Specification<OrderEntity> conDestination(String destination) {
        return (root, query, cb) -> (destination != null && !destination.isBlank()) ? cb.equal(root.get("destination"), destination) : null;
    }

    public static Specification<OrderEntity> entreFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return (root, query, cb) -> {
            if (fechaInicio != null && fechaFin != null) {
                return cb.between(root.get("createdAt"), fechaInicio, fechaFin);
            } else if (fechaInicio != null) {
                return cb.greaterThanOrEqualTo(root.get("createdAt"), fechaInicio);
            } else if (fechaFin != null) {
                return cb.lessThanOrEqualTo(root.get("createdAt"), fechaFin);
            }
            return null;
        };
    }
}
