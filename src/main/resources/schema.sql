-- Crear la tabla franquicias
CREATE TABLE franchises (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Crear la tabla sucursales
CREATE TABLE branches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    franchise_id BIGINT NOT NULL,
    FOREIGN KEY (franchise_id) REFERENCES franchises(id) ON DELETE CASCADE
);

-- Crear la tabla productos
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    branch_id BIGINT NOT NULL,
    FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE CASCADE
);
