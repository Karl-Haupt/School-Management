package za.ac.cput.service.employee.impl;

/*
 EmployeeServiceImplTest.java -> Test's the functionality of the EmployeeServiceImpl
 Student Name: Karl Haupt
 Student Number: 220236585
*/

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.employee.Employee;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.factory.employee.EmployeeFactory;
import za.ac.cput.factory.lookup.NameFactory;
import za.ac.cput.repository.employee.EmployeeRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplTest {

    private Employee employee;
    private Name employeeName;
    @Autowired
    private EmployeeServiceImpl service;
    @Autowired @Mock
    private EmployeeRepository repository;

    @BeforeEach
    void setUp() {
        this.employeeName = NameFactory.buildName("John", "", "Smith");
        this.employee = EmployeeFactory.buildEmployee("id", "john@gmail.com", employeeName);
        Employee saved = this.service.save(this.employee);
        assertEquals(this.employee, saved);
    }

    @AfterEach
    void tearDown() {
        this.service.delete(this.employee);
        List<Employee> employeeList = this.service.findAll();
        assertEquals(0, employeeList.size());
    }

    @Test
    void read() {
        Optional<Employee> read = this.service.read(employee.getStaffID());
        assertAll(
                () -> assertTrue(read.isPresent()),
                () -> assertEquals(employee, read.get())
        );
    }

    @Test
    void deleteById() {
        this.service.deleteById(employee.getStaffID());
        List<Employee> employeeList = this.service.findAll();
        assertAll(
                () -> assertEquals(0, employeeList.size())
        );
    }

    @Test
    void getEmployeeByEmail() {
        var emp = this.service.findEmployeeByEmail("john@gmail.com");
        assertAll(
                () -> assertNotNull(emp),
                () -> assertEquals(this.employee, emp)
        );
    }
}