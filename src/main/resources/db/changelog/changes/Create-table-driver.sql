CREATE TABLE driver_dto
(
    id BINARY(16) NOT NULL,                    -- Para el UUID (128 bits eficientes)
    name VARCHAR(500) NOT NULL,
    license_number VARCHAR(500) NOT NULL,
    active TINYINT(1) DEFAULT 1,
    CONSTRAINT pk_driver PRIMARY KEY (id)
);