package com.system.carRentalManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JoinColumn(unique = true, nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Vehicle vehicle;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @Getter
    private LocalDate startDate;

    @Getter
    private LocalDate endDate;

    @Setter
    private Boolean hasDriver;

    public Booking(Boolean driverOptions, String endDate) {
        this.hasDriver = driverOptions;

        this.startDate = LocalDate.now();

        // PATTERN OF DATE: yyyy-MM-dd
        this.endDate = LocalDate.parse(endDate);
    }

    public boolean hasDriver() {
        return hasDriver;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Booking)) {
            return false;
        }

        Booking that = (Booking) object;

        return vehicle.equals(that.vehicle) && user.equals(that.user) && startDate.equals(that.startDate)
                && endDate.equals(that.endDate) && hasDriver.equals(that.hasDriver);
    }

    @Override
    public int hashCode() {
        int result = vehicle.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + hasDriver.hashCode();

        return result;
    }
}
