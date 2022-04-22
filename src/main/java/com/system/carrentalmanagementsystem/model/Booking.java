package com.system.carrentalmanagementsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
public class Booking implements Serializable {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Vehicle vehicleDetails;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User customerDetails;

//    private Timer timer;
//
    private LocalDate date;

    @Getter
    private Boolean driverOptions;

    public Booking(User user, Vehicle vehicle, Boolean driverOptions) {
        this.customerDetails = user;
        this.vehicleDetails = vehicle;
        this.driverOptions = driverOptions;

        this.date = LocalDate.now();
    }
}
