CREATE TABLE person (
    id INT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    surname VARCHAR(64) NOT NULL,
    cuit BIGINT NOT NULL,
    nationality VARCHAR(20) NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE person_address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT NOT NULL,
    street VARCHAR(32) NOT NULL,
    number INT NOT NULL,
    locality VARCHAR(32) NOT NULL,
    state VARCHAR(20) NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE person_phone (
    id INT AUTO_INCREMENT PRIMARY KEY,
    person_id INT NOT NULL,
    area_code INT,
    number BIGINT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE
);