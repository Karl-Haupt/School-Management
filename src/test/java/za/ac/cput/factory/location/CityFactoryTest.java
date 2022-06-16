package za.ac.cput.factory.location;
/*
 This is the city factory test cases
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;

import static org.junit.jupiter.api.Assertions.*;

class CityFactoryTest {
    private City city;
    private Country country;

    @BeforeEach
    public void startUp(){
        country = CountryFactory.buildCountry("1-RSA","South Africa");
        city = CityFactory.buildCity("1-CPT", "Cape Town",country);
    }
    @Test
    public void createCityTest(){
        assertAll(
                () -> assertEquals("1-CPT", city.getId()),
                () -> assertNotEquals(null,city),
                () -> assertNotNull(city.getName()),
                () -> assertNotNull(city.getCountry())
        );
    }

    @Test
    public void testForNullCountryObject(){
        Exception  emptyCountry = assertThrows(IllegalArgumentException.class, () ->
        {
            CityFactory.buildCity("1-CPT", "Cape Town",null);
        });

        String returnedError = "Invalid value for params: Country";
        assertTrue(emptyCountry.getMessage().contains(returnedError));

    }    @Test
    public void testForEmptyCityID(){
        Exception  emptyCity = assertThrows(IllegalArgumentException.class, () ->
        {
           CityFactory.buildCity("", "Cape Town",country);
        });

        String returnedError = "Invalid value for params: City ID";
        assertTrue(emptyCity.getMessage().contains(returnedError));
    }

}