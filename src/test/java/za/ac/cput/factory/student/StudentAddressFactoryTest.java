package za.ac.cput.factory.student;
/*
 This is the factory test cases of Student Address
 Mponeng Ratego
 216178991
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.student.StudentAddress;
import za.ac.cput.domain.lookup.Address;
import za.ac.cput.domain.location.City;
import za.ac.cput.factory.employee.EmployeeAddressFactory;
import za.ac.cput.factory.lookup.AddressFactory;


import static org.junit.jupiter.api.Assertions.*;

class StudentAddressFactoryTest {

    private StudentAddress studentAddress;
    private Address address;
    private City city;

    @BeforeEach
    void setUp() {
        address = AddressFactory.build("unitNumber", "complexName", "streetNumber", "streetName", 7570, city);

        studentAddress =StudentAddressFactory
                .build("studentId", address);

    }

    @Test
    void testStudentAddressCreation() {
        assertAll(
                () -> assertNotNull(studentAddress),
                () -> assertNotNull(studentAddress.getStudentId()),
                () -> assertEquals("studentId", studentAddress.getStudentId()),
                () -> assertNotNull(studentAddress.getAddress())
        );
    }

    @Test
    void testStudentIdForEmptyStudentID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StudentAddressFactory.build("", address);
        });

        String expectedMessage = "Invalid value for params: Student ID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testStudentIdForNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StudentAddressFactory.build(null, address);
        });

        String expectedMessage = "Invalid value for params: staffID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



}