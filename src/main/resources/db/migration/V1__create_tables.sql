CREATE TABLE proveedor (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255),
    codigo_proveedor VARCHAR(7)
);

GRANT ALL PRIVILEGES ON TABLE proveedor TO jpa_user;

CREATE TABLE destino (
    id UUID PRIMARY KEY,
    codigo VARCHAR(1),
    descripcion VARCHAR(255)
);

GRANT ALL PRIVILEGES ON TABLE destino TO jpa_user;

CREATE TABLE producto (
    id UUID PRIMARY KEY,
    codigo_producto VARCHAR(5),
    nombre VARCHAR(255)
);

GRANT ALL PRIVILEGES ON TABLE producto TO jpa_user;