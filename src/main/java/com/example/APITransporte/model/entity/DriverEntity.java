package com.example.APITransporte.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "driver_dto")
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.BINARY) // Obliga a Hibernate a mapear el UUID como BINARY(16) en MySQL
     UUID id;
    @Column
     String name;
    @Column(name = "license_number")
     String licenseNumber;
    @Column
     Boolean active;


}
