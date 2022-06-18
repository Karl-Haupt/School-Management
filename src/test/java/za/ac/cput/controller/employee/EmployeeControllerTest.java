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
import za.ac.cput.controller.location.CityController;
import za.ac.cput.controller.location.CountryController;
import za.ac.cput.domain.employee.Employee;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.factory.employee.EmployeeAddressFactory;
import za.ac.cput.factory.employee.EmployeeFactory;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.factory.lookup.AddressFactory;
import za.ac.cput.factory.lookup.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private EmployeeController controller;
    @Autowired private EmployeeAddressController addressController;
    @Autowired private CityController cityController;
    @Autowired private CountryController countryController;

    @Autowired private TestRestTemplate restTemplate;

    private Employee employee;
    private String employeeBaseURL;


    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        Name employeeName = NameFactory.buildName("John", "", "Wood");
        this.employee = EmployeeFactory.buildEmployee("1", "john@gmail.com", employeeName);
        this.employeeBaseURL = "http://localhost:" + this.port + "/api/v1/school-management/employee/";
        System.out.println(employeeBaseURL);
    }

    @Test
    @Order(1)
    void save() {
        String url = employeeBaseURL + "save";
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
        String url = employeeBaseURL + "read/" + employee.getStaffID();
        ResponseEntity<Employee> response = this.restTemplate.getForEntity(url, Employee.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(3)
    void findEmployeeByEmail() {
        String url = employeeBaseURL + "read?email=" + employee.getEmail();
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(4)
    void findEmployeesByCity() {
        AddCityAndCountryToDB();
        String url = employeeBaseURL + "read/city/1";
        ResponseEntity<String[]> response = this.restTemplate.getForEntity(url, String[].class);
        for (var n : response.getBody()) {
            System.out.println(n);
        }
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(response.getBody().length == 1)
        );
    }

    void AddCityAndCountryToDB() {
        var country = CountryFactory.buildCountry("15", "RSA");
        var city = CityFactory.buildCity("1", "Boston", country);
        var address = AddressFactory.build("13", "Complex Name", "123", "streetName", 1234, city);
        var employeeAddress = EmployeeAddressFactory.build(employee.getStaffID(), address);

//        controller.save(employee);
        countryController.save(country);
        cityController.save(city);
        addressController.save(employeeAddress);
    }

    @Test
    @Order(7)
    void delete() {
        String url = employeeBaseURL + "delete";
        this.restTemplate.delete(url);
    }

    @Test
    @Order(5)
    void deleteById() {
        String url = employeeBaseURL + "delete/" + this.employee.getStaffID();
        this.restTemplate.delete(url);
    }

    @Test
    @Order(6)
    void findAll() {
        String url = employeeBaseURL + "all";
        ResponseEntity<Employee[]> response = this.restTemplate.getForEntity(url, Employee[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(response.getBody().length == 0)
        );
    }

}