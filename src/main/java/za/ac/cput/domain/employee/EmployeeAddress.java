package za.ac.cput.domain.employee;

/*
 EmployeeAddress.java domain
 Student Name: Mike Somelezo Tyolani
 Student Number: 220187568
*/

import za.ac.cput.domain.lookup.Address;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class EmployeeAddress implements Serializable {

    @NotNull
    @Id
    private String staffId;
    @Embedded
    private Address address;

    protected EmployeeAddress() {}

    private EmployeeAddress(Builder builder) {
        this.staffId = builder.staffId;
        this.address = builder.address;
    }

    public String getStaffId() {
        return staffId;
    }

    public Address getAddress() {
        return address;
    }

    public static class Builder {

        private String staffId;
        private Address address;

        public EmployeeAddress.Builder staffId (String staffId) {
            this.staffId = staffId;
            return this;
        }

        public EmployeeAddress.Builder Address (Address address ) {
            this.address = address;
            return this;
        }


        public EmployeeAddress.Builder copy (EmployeeAddress employeeAddress){
            this.staffId = employeeAddress.staffId;
            this.address = employeeAddress.address;
            return this;

        }

        public EmployeeAddress build() { return new EmployeeAddress(this);}
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "staffId='" + staffId + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeAddress that = (EmployeeAddress) o;
        return staffId.equals(that.staffId) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId, address);
    }
}
