CREATE TABLE person (
    id INTEGER PRIMARY KEY,
    name VARCHAR NOT NULL,
    surname VARCHAR NOT NULL,
    cuit VARCHAR NOT NULL,
    nationality VARCHAR NOT NULL,
    active INTEGER DEFAULT 1 NOT NULL
);

CREATE TABLE person_address (
    id SERIAL PRIMARY KEY,
    person_id INTEGER REFERENCES person (id) NOT NULL,
    street VARCHAR NOT NULL,
    number VARCHAR NOT NULL,
    locality VARCHAR NOT NULL,
    state VARCHAR NOT NULL
);

CREATE TABLE person_phone (
    id SERIAL PRIMARY KEY,
    person_id INTEGER REFERENCES person (id) NOT NULL,
    area_code VARCHAR,
    number VARCHAR NOT NULL
);