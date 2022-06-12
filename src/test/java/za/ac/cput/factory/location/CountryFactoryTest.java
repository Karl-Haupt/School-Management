package za.ac.cput.factory.location;

/* CountryFactoryTest.java
   Factory Test Case for the Country
   Author: Joshua Daniel Jonkers(215162668)
   Date: 11/06/2022
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.location.Country;

import static org.junit.jupiter.api.Assertions.*;

class CountryFactoryTest {
    private Country country;

    @BeforeEach
    void setUp() {
        country = CountryFactory.buildCountry("ZA", "South Africa");
    }

    @Test
    void testCreationOfCountry() {
        assertAll(
                () -> assertNotNull(country),
                () -> assertNotNull(country.getCountryID()),
                () -> assertNotNull(country.getCountryName())
        );
    }

    @Test
    void testCountryIDForEmptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> CountryFactory.buildCountry("", "South Africa"));

        String expectedMessage = "Invalid value for countryID.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCountryIDForNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> CountryFactory.buildCountry("", "South Africa"));

        String expectedMessage = "Invalid value for countryID.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testInvalidCountryName() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> CountryFactory.buildCountry("ZA", ""));

        String expectedMessage = "Invalid value for countryName.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}