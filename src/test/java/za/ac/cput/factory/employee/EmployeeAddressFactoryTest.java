package za.ac.cput.factory.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.employee.EmployeeAddress;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.lookup.Address;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.lookup.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeAddressFactoryTest {
    private EmployeeAddress employeeAddress;
    private Address address;
    private City city;

    @BeforeEach
    void setUp() {
        address = AddressFactory.build("unit-number", "complex-name", "street-number", "street-name", 1234, city);

        employeeAddress = EmployeeAddressFactory
                .build("staff-id", address);

    }

    @Test
    void testEmployeeAddressCreation() {
        assertAll(
                () -> assertNotNull(employeeAddress),
                () -> assertNotNull(employeeAddress.getStaffId()),
                () -> assertEquals("staff-id", employeeAddress.getStaffId()),
                () -> assertNotNull(employeeAddress.getAddress())
        );
    }

    @Test
    void testStaffIDForEmptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeAddressFactory.build("", address);
        });

        String expectedMessage = "Invalid value for params: staffID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testStaffIDForNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeAddressFactory.build(null, address);
        });

        String expectedMessage = "Invalid value for params: staffID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



}