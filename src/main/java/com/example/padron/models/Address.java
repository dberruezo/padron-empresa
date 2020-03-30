package com.example.padron.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name = "person_address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private String street;
    private Integer number;
    private String locality;
    private String state;

    @ManyToOne
    private Person person;

    public Address () {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("personId=" + person.getId())
            .add("street='" + street + "'")
            .add("number=" + number)
            .add("locality='" + locality + "'")
            .add("state='" + state + "'")
            .toString();
    }
}
