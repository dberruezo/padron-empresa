package com.example.padron.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name = "person_address")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 32)
    private String street;
    private Integer number;
    @Column(length = 32)
    private String locality;
    @Column(length = 20)
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
        Integer personId = null;

        if (this.getPerson() != null) {
            personId = getPerson().getId();
        }

        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("personId=" + personId)
            .add("street='" + street + "'")
            .add("number=" + number)
            .add("locality='" + locality + "'")
            .add("state='" + state + "'")
            .toString();
    }
}
