CREATE TABLE order_dto
(
    id BINARY(16) NOT NULL,                    -- Para el UUID (128 bits eficientes)
    status VARCHAR(50) NOT NULL,               -- Para el Enum (Guardado como texto)
    origin VARCHAR(500) NOT NULL,
    destination VARCHAR(500) NOT NULL,
    created_at DATETIME(6) NOT NULL,           -- Para LocalDateTime (con precisión de 6 decimales)
    updated_at DATETIME(6) NOT NULL,           -- Para LocalDateTime (con precisión de 6 decimales)
    CONSTRAINT pk_orders PRIMARY KEY (id)
);