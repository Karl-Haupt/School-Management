package za.ac.cput.factory.location;
/*
 This is the city factory
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.helper.Validator;

public class CityFactory {
    public static City buildCity(String id, String CityName, Country country){
        Validator.checkStringParam("City ID", id);
        Validator.checkStringParam("City Name",CityName);
        Validator.isNull("Country", country);
        return new City.CityBuilder()
                .setId(id)
                .setCityName(CityName)
                .setCountry(country)
                .build();
    }
}
