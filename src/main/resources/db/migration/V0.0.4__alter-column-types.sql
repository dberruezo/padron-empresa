ALTER TABLE person
    ALTER COLUMN cuit TYPE NUMERIC(11) USING cuit::numeric(11),
    ALTER COLUMN active DROP DEFAULT,
    ALTER COLUMN active TYPE BOOLEAN USING active::boolean,
    ALTER COLUMN active SET DEFAULT TRUE
;

ALTER TABLE person_phone
    ALTER COLUMN area_code TYPE INTEGER USING number::integer,
    ALTER COLUMN number TYPE BIGINT USING number::bigint
;