package za.ac.cput.domain.student;
/*
 This is the student address domain/entity class
 Mponeng Ratego
 216178991
*/
import za.ac.cput.domain.lookup.Address;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
public class StudentAddress {

    @NotNull
    @Id
    private String studentId;


    @Embedded
    private Address address;

    protected StudentAddress() {}

    private StudentAddress (Builder builder) {
        this.studentId = builder.studentId;
        this.address = builder.address;
    }

    public String getStudentId(){
      return studentId;
    }

    public Address getAddress(){
        return address;
    }

    @Override
    public String toString() {
        return "StudentAddress{" +
                "StudentId='" + studentId + '\'' +
                ", Address='" + address
                + '\'' +
                '}';
    }

    public static class Builder {
        private String studentId;
        private Address address;

        public Builder setStudentId(String studentId){
            this.studentId = studentId;
            return this;
        }

        public Builder setAddress(Address address){
            this.address = address;
            return this;
        }

        public Builder copy(StudentAddress studentAddress) {
            this.studentId = studentAddress.studentId;
            this.address = studentAddress.address;

            return this;
        }


        public StudentAddress build() {
            return new StudentAddress(this);
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentAddress)) return false;
        StudentAddress studentAddress = (StudentAddress) o;
        return studentId.equals(studentAddress.studentId) && address.equals(studentAddress.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, address);
    }

}
