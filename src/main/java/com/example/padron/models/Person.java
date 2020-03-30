package com.example.padron.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

@Entity
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private String name;
    private String surname;
    private Long cuit;
    private String nationality;
    private Boolean active;

    @OneToMany(mappedBy = "person")
    private List<Address> addresses;
    @OneToMany(mappedBy = "person")
    private List<Phone> contactNumbers;

    public Person () {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Phone> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(List<Phone> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("name='" + name + "'")
            .add("surname='" + surname + "'")
            .add("cuit=" + cuit)
            .add("nationality='" + nationality + "'")
            .add("active=" + active)
            .toString();
    }
}