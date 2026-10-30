package com.example.APITransporte.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "asignacion_orden")
public class AsignacionOrdenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JdbcTypeCode(Types.BINARY)
    @Column(name = "id_order")
    private UUID idOrder;
    @JdbcTypeCode(Types.BINARY)
    @Column(name = "id_driver")
    private UUID idDriver;
    @Column(name = "ruta_asignacion_pdf")
    private String rutaAsignacionPdf;
    @Column(name = "ruta_imagen")
    private String rutaImagen;
}
