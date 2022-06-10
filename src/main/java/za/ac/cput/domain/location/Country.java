package za.ac.cput.domain.location;

/* Country.java
   Entity for the Country
   Author: Joshua Daniel Jonkers(215162668)
   Date: 10/06/2022
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Country {
    @Id
    @NotNull
    private String countryID;
    @NotNull
    private String countryName;

    protected Country() {}

    public Country(Country.Builder builder) {
        this.countryID = builder.countryID;
        this.countryName = builder.countryName;
    }

    public String getCountryID() { return countryID; }

    public String getCountryName() { return countryName; }

    @Override
    public String toString() {
        return "Country{" +
                "countryID='" + countryID + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }

    public static class Builder {
        private String countryID;
        private String countryName;

        public Country.Builder setCountryID(String countryID) {
            this.countryID = countryID;
            return this;
        }

        public Country.Builder setCountryName(String countryName) {
            this.countryName = countryName;
            return this;
        }

        public Country.Builder copy(Country country) {
            this.countryID = country.countryID;
            this.countryName = country.countryName;
            return this;
        }

        public Country build() { return new Country(this); }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return countryID.equals(country.countryID) && countryName.equals(country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryID, countryName);
    }
}
