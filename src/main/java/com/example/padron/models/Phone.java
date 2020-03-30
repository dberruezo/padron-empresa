package com.example.padron.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name = "person_phone")
public class Phone implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    @Column(name = "area_code")
    private Integer areaCode;
    private Long number;

    public Phone () {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Phone.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("areaCode=" + areaCode)
            .add("number=" + number)
            .toString();
    }
}
