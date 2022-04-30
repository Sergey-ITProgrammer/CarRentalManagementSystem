package com.system.carRentalManagementSystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
public class Driver implements Serializable {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Long age;

    @Getter
    @Setter
    private String contactNo;

    @Getter
    @Setter
    private String emailAddress;

    @Getter
    @Setter
    private String driverLicence;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn
    private Booking booking;

    public Driver(String name, Long age, String contactNo, String emailAddress, String driverLicence) {
        this.name = name;
        this.age = age;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.driverLicence = driverLicence;
    }
}
