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
    private String name;
    @ManyToOne
    @JoinColumn(name = "FK_countryID", referencedColumnName = "countryID")
    @NotNull
    private Country country;

    public void setCountry(Country country) {
        this.country = country;
    }

    protected City() {
    }

    public City(CityBuilder cityBuilder) {
        this.id = cityBuilder.id;
        name = cityBuilder.name;
        this.country = cityBuilder.country;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", CityName='" + name + '\'' +
                ", country=" + country +
                '}';
    }

    public static class CityBuilder{
        private String id;
        private String name;
        private Country country;

        public CityBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public CityBuilder setName(String name) {
            this.name= name;
            return this;
        }

        public CityBuilder setCountry(Country country) {
            this.country = country;
            return this;
        }
        public CityBuilder copy(City city){
            this.id = city.id;
            this.name = city.name;
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
        return id.equals(city.id) && name.equals(city.name) && country.equals(city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}