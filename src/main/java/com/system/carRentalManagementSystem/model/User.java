package com.system.carRentalManagementSystem.model;

import com.system.carRentalManagementSystem.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Role role = Role.USER;

    @Getter
    @Setter
    @OneToMany(mappedBy = "customerDetails", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<Booking> bookings = new ArrayList<>();

    @Getter
    @Setter
    private String contactNo;

    @Getter
    @Setter
    private String emailAddress;

    @Getter
    @Setter
    private String DOB;

    @Getter
    @Setter
    private String driverLicence;

    public User(String name, String contactNo, String emailAddress, String DOB, String driverLicence) {
        this.name = name;
        this.contactNo = contactNo;
        this.emailAddress = emailAddress;
        this.DOB = DOB;
        this.driverLicence = driverLicence;
    }
}