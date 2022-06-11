package za.ac.cput.controller.employee;

/*
 EmployeeControllerTest.java -> Test the controller functionality
 Student Name: Karl Haupt
 Student Number: 220236585
*/

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.employee.Employee;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.factory.employee.EmployeeFactory;
import za.ac.cput.factory.lookup.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private EmployeeController controller;
    @Autowired
    private TestRestTemplate restTemplate;

    private Employee employee;
    private Name employeeName;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.employeeName = NameFactory.buildName("John", "", "Wood");
        this.employee = EmployeeFactory.buildEmployee("employee-id", "john@gmail.com", employeeName);
        this.baseUrl = "http://localhost:" + this.port + "api/v1/school-management/employee/";
    }

    @Test
    @Order(1)
    void save() {
        String url = baseUrl + "save";
        ResponseEntity<Employee> response = this.restTemplate.postForEntity(
                url, this.employee, Employee.class
        );
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(2)
    void read() {
        String url = baseUrl + "read/" + employee.getStaffID();
        ResponseEntity<Employee> response = this.restTemplate.getForEntity(url, Employee.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    //TODO: Delete employee by employee entity
    @Test
    void delete() {
    }

    @Test
    @Order(3)
    void deleteById() {
        String url = baseUrl + "delete/" + this.employee.getStaffID();
        this.restTemplate.delete(url);
    }

    @Test
    @Order(4)
    void findAll() {
        String url = baseUrl + "all";
        ResponseEntity<Employee[]> response = this.restTemplate.getForEntity(url, Employee[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(0, response.getBody()),
                () -> assertTrue(response.getBody().length == 0)
        );
    }

    //TODO: Test the getEmployeeByEmail
}