package za.ac.cput.domain.location;
/*
 This is the city builder it is an entity in the database
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import javax.persistence.*;
import za.ac.cput.domain.location.Country;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class City {
    @Id
    @NotNull
    private String id;
    @NotNull
    private String CityName;
    @ManyToOne
    @JoinColumn(name = "countryID")
    @NotNull
    private Country country;

    public void setCountry(Country country) {
        this.country = country;
    }

    protected City() {
    }

    public City(CityBuilder cityBuilder) {
        this.id = cityBuilder.id;
        CityName = cityBuilder.CityName;
        this.country = cityBuilder.country;
    }

    public String getId() {
        return id;
    }

    public String getCityName() {
        return CityName;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", CityName='" + CityName + '\'' +
                ", country=" + country +
                '}';
    }

    public static class CityBuilder{
        private String id;
        private String CityName;
        private Country country;

        public CityBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public CityBuilder setCityName(String cityName) {
            CityName = cityName;
            return this;
        }

        public CityBuilder setCountry(Country country) {
            this.country = country;
            return this;
        }
        public CityBuilder copy(City city){
            this.id = city.id;
            this.CityName = city.CityName;
            this.country = city.country;
            return this;
        }
        public City build(){
            return new City(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return id.equals(city.id) && CityName.equals(city.CityName) && country.equals(city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, CityName, country);
    }
}