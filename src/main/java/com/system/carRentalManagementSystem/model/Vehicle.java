package com.system.carRentalManagementSystem.model;

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
    @Column(unique = true)
    private String registrationNo;

    @Getter
    @Setter
    private String modelNo;

    @Getter
    @Setter
    private Boolean availability;

    public Vehicle(String registrationNo, String modelNo) {
        this.registrationNo = registrationNo;
        this.modelNo = modelNo;
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

        return this.registrationNo.equals(that.registrationNo) && this.modelNo.equals(that.modelNo)
                && this.availability.equals(that.availability);
    }

    @Override
    public int hashCode() {
        int result = registrationNo.hashCode();
        result = 31 * result + modelNo.hashCode();
        result = 31 * result + availability.hashCode();

        return result;
    }
}
