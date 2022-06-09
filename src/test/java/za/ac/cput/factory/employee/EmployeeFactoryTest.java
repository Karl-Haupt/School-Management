package za.ac.cput.factory.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.employee.Employee;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFactoryTest {
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = EmployeeFactory
                .buildEmployee("staff-id",
                        "john@gmail.com",
                        new Name("John", "Smith"));
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
                    new Name("John", "Smith"));
        });

        String expectedMessage = "Invalid value for params: staffID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testStaffIDForNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeFactory.buildEmployee("", "john@gmail.com",
                    new Name("John", "Smith"));
        });

        String expectedMessage = "Invalid value for params: staffID";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeFactory.buildEmployee("staff-id", "johngmailcom",
                    new Name("John", "Smith"));
        });

        String expectedMessage = "Invalid value for params: email";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void testInvalidName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeFactory.buildEmployee("staff-id", "john@gmail.com",
                    new Name("", ""));
        });

        String expectedMessage = "Invalid value for params: name";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}