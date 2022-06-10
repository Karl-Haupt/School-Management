package za.ac.cput.domain.lookup;

/*
 Address.java value object domain
 Student Name: Mike Somelezo Tyolani
 Student Number: 220187568
*/

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {

    private String unitNUmber;
    private String complexName;
    @NotNull
    private String streetNUmber;
    @NotNull
    private String streetName;
    @NotNull
    private int postalCode;
    @NotNull
    @Embedded
    private City city;

    protected Address(){}

    private Address(Builder builder) {
        this.unitNUmber = builder.unitNUmber;
        this.complexName = builder.complexName;
        this.streetNUmber = builder.streetNUmber;
        this.streetName = builder.streetName;
        this.postalCode = builder.postalCode;
        this.city = builder.city;
    }

    public String getUnitNUmber() {
        return unitNUmber;
    }

    public String getComplexName() {
        return complexName;
    }

    public String getStreetNUmber() {
        return streetNUmber;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public City getCity() {
        return city;
    }

    public static class Builder {

        private String unitNUmber,complexName, streetNUmber, streetName;
        private  int postalCode;
        private City city;

        public Builder unitNUmber (String unitNUmber) {
            this.unitNUmber = unitNUmber;
            return this;
        }

        public Builder complexName (String complexName) {
            this.complexName = complexName;
            return this;
        }

        public Builder streetName (String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder streetNUmber (String streetNUmber) {
            this.streetNUmber = streetNUmber;
            return this;
        }

        public Builder postalCode (int postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder City (City city ) {
            this.city = city;
            return this;
        }


        public Builder copy (Address address){
            this.unitNUmber = address.unitNUmber;
            this.complexName = address.complexName;
            this.streetName = address.streetName;
            this.streetNUmber = address.streetNUmber;
            this.postalCode = address.postalCode;
            this.city = address.city;
            return this;

        }

        public Address build() { return new Address(this);}
    }


}
