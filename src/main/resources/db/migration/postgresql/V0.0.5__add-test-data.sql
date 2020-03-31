INSERT INTO person VALUES (
    12345678,
    'Juan',
    'Perez',
    20365526312,
    'argentina'
);

INSERT INTO person_address (
	person_id, street, number, locality, state
) VALUES (
    12345678,
    'Virrey del Pino',
    2617,
    'Ciudad Autónoma de Buenos Aires',
    'Buenos Aires'
);

INSERT INTO person_phone (
	person_id, area_code, number
) VALUES (
    12345678,
    11,
    47862188
);