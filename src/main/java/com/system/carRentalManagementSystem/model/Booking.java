package com.system.carRentalManagementSystem.model;

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

    @Getter
    private LocalDate startDate;

    @Getter
    private LocalDate endDate;

    @Getter
    @Setter
    private Boolean driverOptions;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn
    private User driverDetails;

    public Booking(Boolean driverOptions) {
        this.driverOptions = driverOptions;

        this.startDate = LocalDate.now();
    }
}
