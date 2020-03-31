ALTER TABLE person_phone DROP CONSTRAINT person_phone_person_id_fkey;
ALTER TABLE person_address DROP CONSTRAINT person_address_person_id_fkey;

ALTER TABLE person_phone
    ADD CONSTRAINT person_phone_person_id_fkey
    FOREIGN KEY (person_id) REFERENCES person (id)
    ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE person_address
    ADD CONSTRAINT person_address_person_id_fkey
    FOREIGN KEY (person_id) REFERENCES person (id)
    ON DELETE CASCADE ON UPDATE CASCADE;