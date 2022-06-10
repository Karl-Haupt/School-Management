package za.ac.cput.domain.employee;

import za.ac.cput.domain.lookup.Name;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/*
 Employee.java -> Represent an entity in the database
 Student Name: Karl Haupt
 Student Number: 220236585
*/

@Entity
public class Employee {
    @NotNull @Id
    private String staffID;
    @NotNull
    private String email;
    @NotNull
    private Name name;

    protected Employee() {}

    public Employee(Builder builder) {
        this.staffID = builder.staffID;
        this.email = builder.email;
        this.name = builder.name;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getEmail() {
        return email;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "staffID='" + staffID + '\'' +
                ", email='" + email + '\'' +
                ", name=" + name +
                '}';
    }

    public static class Builder {
        private String staffID;
        private String email;
        private Name name;

        public Builder setStaffID(String staffID) {
            this.staffID = staffID;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(Name name) {
            this.name = name;
            return this;
        }

        public Builder copy(Employee employee) {
            this.staffID = employee.staffID;
            this.email = employee.email;
            this.name = employee.name;
            return this;
        }

        public Employee build() { return new Employee(this); }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(staffID, employee.staffID) && Objects.equals(email, employee.email) && Objects.equals(name, employee.name);
    }

}
