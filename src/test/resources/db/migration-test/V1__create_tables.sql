CREATE TABLE proveedor (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255),
    codigo_proveedor VARCHAR(7)
);

CREATE TABLE destino (
    id UUID PRIMARY KEY,
    codigo VARCHAR(1),
    descripcion VARCHAR(255)
);

CREATE TABLE producto (
    id UUID PRIMARY KEY,
    codigo_producto VARCHAR(5),
    nombre VARCHAR(255)
);