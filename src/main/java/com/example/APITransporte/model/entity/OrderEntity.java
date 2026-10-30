package com.example.APITransporte.model.entity;

import com.example.APITransporte.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_dto")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.BINARY) // Obliga a Hibernate a mapear el UUID como BINARY(16) en MySQL
    private UUID id;
    @Column
    @Enumerated(EnumType.STRING) // Guarda el nombre del enum ("PENDING") en lugar de su índice numérico (0)
    private Status status;
    @Column
    private String origin;
    @Column
    private String destination;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
