package za.ac.cput.factory.employee;

/*
 EmployeeFactoryTest.java -> Test the EmployeeFactory class
 Student Name: Karl Haupt
 Student Number: 220236585
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.employee.Employee;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.factory.lookup.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFactoryTest {
    private Employee employee;
    private Name employeeName;

    @BeforeEach
    void setUp() {
        employeeName = NameFactory.buildName("John", "", "Smith");

        employee = EmployeeFactory
                .buildEmployee("staff-id",
                        "john@gmail.com",
                        employeeName);
    }

    @Test
    void testEmployeeCreation() {
        assertAll(
                () -> assertNotNull(employee),
                () -> assertNotNull(employee.getStaffID()),
                () -> assertEquals("john@gmail.com", employee.getEmail()),
                () -> assertNotNull(employee.getName())
        );
    }

    @Test
    void testStaffIDForEmptyString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeFactory.buildEmployee("", "john@gmail.com",
                    employeeName);
        });

        String expectedMessage = "Invalid value for params: staffID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testStaffIDForNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeFactory.buildEmployee(null, "john@gmail.com",
                    employeeName);
        });

        String expectedMessage = "Invalid value for params: staffID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeFactory.buildEmployee("staff-id", "johngmailcom",
                    employeeName);
        });

        String expectedMessage = "Invalid value for params: email";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}