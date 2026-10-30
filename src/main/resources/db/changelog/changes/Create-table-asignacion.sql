
CREATE TABLE asignacion_orden
(
    id                  BIGINT NOT NULL AUTO_INCREMENT,
    id_order            BINARY(16),
    id_driver          BINARY(16),
    ruta_asignacion_pdf VARCHAR(500),
    ruta_imagen         VARCHAR(500),
    CONSTRAINT pk_asignacion PRIMARY KEY (id)
)