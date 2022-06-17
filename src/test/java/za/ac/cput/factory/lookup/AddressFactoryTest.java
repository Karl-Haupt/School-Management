package za.ac.cput.factory.lookup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.employee.EmployeeAddress;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.domain.lookup.Address;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;

import static org.junit.jupiter.api.Assertions.*;

class AddressFactoryTest {
    private Address address;
    private City city;
    private Country country;

    @BeforeEach
    void setUp() {
        address = AddressFactory.build("unit-number", "complex-name", "street-number", "street-name", 1234, city);

        city = CityFactory
                .buildCity("staff-id","" ,country);

        country = CountryFactory.buildCountry("country-id","country-name");

    }

    @Test
    void testAddressCreation() {
        assertAll(
                () -> assertNotNull(address),
                () -> assertNotNull(address.getComplexName()),
                () -> assertEquals("street-number", address.getStreetNUmber()),
                () -> assertNotNull(address.getCity())
        );
    }

    @Test
    void testNullStreetName()
    {
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()->  AddressFactory.build("unit-number", "complex-name", "street-number", null, 1234, city));
        String expectedMessage = "Invalid value for params: streetName";
        String returnedMessage = exception.getMessage();
        assertEquals(expectedMessage, returnedMessage);
    }

    @Test
    void testStreetNumberForEmptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AddressFactory.build("unit-number", "complex-name", "", "street-name", 1234, city);
        });

        String expectedMessage = "Invalid value for params: streetNumber";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testInvalidPostalCode() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AddressFactory.build("unit-number", "complex-name", "street-number", "street-name", 123, city);
        });

        String expectedMessage = "Invalid value for params: postalCode";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}