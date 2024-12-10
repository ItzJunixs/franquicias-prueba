-- Crear la tabla franquicias
CREATE TABLE IF NOT EXISTS franchises (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Crear la tabla sucursales
CREATE TABLE IF NOT EXISTS branches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    franchise_id BIGINT NOT NULL,
    FOREIGN KEY (franchise_id) REFERENCES franchises(id) ON DELETE CASCADE
);

-- Crear la tabla productos
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    stock BIGINT NOT NULL,
    branch_id BIGINT NOT NULL,
    FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE CASCADE
);

-- Insertar datos en la tabla franquicias
INSERT INTO franchises (name) VALUES
    ('Franquicia Ejemplo 1'),
    ('Franquicia Ejemplo 2'),
    ('Franquicia Ejemplo 3');

-- Insertar datos en la tabla sucursales
INSERT INTO branches (name, franchise_id) VALUES
    ('Sucursal A', 1),
    ('Sucursal B', 1),
    ('Sucursal C', 2),
    ('Sucursal D', 3);

-- Insertar datos en la tabla productos
INSERT INTO products (name, stock, branch_id) VALUES
    ('Producto 1', 100, 1),
    ('Producto 2', 50, 1),
    ('Producto 3', 200, 2),
    ('Producto 4', 300, 3),
    ('Producto 5', 150, 3),
    ('Producto 6', 400, 4);
