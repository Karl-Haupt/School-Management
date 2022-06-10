package za.ac.cput.domain;
import za.ac.cput.domain.Address;


public class StudentAddress {

    private String studentId;
    private Address address;




    private StudentAddress(Builder builder) {
        this.studentId = builder.studentId;
        this.address = builder.address;
        
    }

    public String getStudentId(){
      return studentId;
    }

    public String getAddress(){
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




}
