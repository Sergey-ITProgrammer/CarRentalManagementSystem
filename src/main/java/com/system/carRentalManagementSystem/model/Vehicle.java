package com.system.carRentalManagementSystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Vehicle {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(unique = true)
    private String registrationNo;

    @Getter
    @Setter
    private String model;

    @Getter
    @Setter
    private Boolean availability;

    public Vehicle(String registrationNo, String model) {
        this.registrationNo = registrationNo;
        this.model = model;
        this.availability = true;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Vehicle)) {
            return false;
        }

        Vehicle that = (Vehicle) object;

        return this.registrationNo.equals(that.registrationNo) && this.model.equals(that.model)
                && this.availability.equals(that.availability);
    }

    @Override
    public int hashCode() {
        int result = registrationNo.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + availability.hashCode();

        return result;
    }
}
