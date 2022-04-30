package com.system.carRentalManagementSystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@Entity
public class Admin implements Serializable {
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
    private String contactNo;

    @Getter
    @Setter
    private String emailAddress;

    public Admin(String name, String contactNo, String emailAddress) {
        this.name = name;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
    }
}
