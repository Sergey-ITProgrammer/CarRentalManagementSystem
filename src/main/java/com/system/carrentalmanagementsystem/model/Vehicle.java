package com.system.carrentalmanagementsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
public class Vehicle implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String registrationNo;

    @Getter
    @Setter
    private String modelNo;

    @Getter
    private boolean hasDriver;

    @Getter
    private Boolean availability;

    public Vehicle(String registrationNo, String modelNo, boolean hasDriver) {
        this.registrationNo = registrationNo;
        this.modelNo = modelNo;
        this.availability = true;
        this.hasDriver = hasDriver;
    }

    public void setAvailability() {
        this.availability = true;
    }

    public void setUnavailability() {
        this.availability = false;
    }
}
