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
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.factory.employee.EmployeeAddressFactory;
import za.ac.cput.factory.employee.EmployeeFactory;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.factory.lookup.AddressFactory;
import za.ac.cput.factory.lookup.NameFactory;
import za.ac.cput.service.employee.impl.EmployeeAddressServiceImpl;
import za.ac.cput.service.employee.impl.EmployeeServiceImpl;
import za.ac.cput.service.location.impl.CityServiceImpl;
import za.ac.cput.service.location.impl.CountryServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private EmployeeController controller;
    @Autowired private TestRestTemplate restTemplate;

    private Employee employee;
    private String baseUrl;

    @Autowired
    private EmployeeServiceImpl service;
    @Autowired private CityServiceImpl cityService;
    @Autowired private CountryServiceImpl countryService;
    @Autowired private EmployeeAddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        Name employeeName = NameFactory.buildName("John", "", "Wood");
        this.employee = EmployeeFactory.buildEmployee("1", "john@gmail.com", employeeName);
        this.baseUrl = "http://localhost:" + this.port + "/api/v1/school-management/employee/";
        System.out.println(baseUrl);
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

    @Test
    @Order(3)
    void findEmployeeByEmail() {
        String url = baseUrl + "read?email=" + employee.getEmail();
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
//    @Order(4)
//    void findEmployeesByCity() {
//        var country = CountryFactory.buildCountry("15", "RSA");
//        var city = CityFactory.buildCity("1", "Boston", country);
//        AddCityAndCountryToDB(city, country);
//        String url = baseUrl + "read/city/" + city.getId();
//        ResponseEntity<String[]> response = this.restTemplate.getForEntity(url, String[].class);
//        assertAll(
//                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
//                () -> assertTrue(response.getBody().length == 0)
//        );
//    }

    private void AddCityAndCountryToDB(City city, Country country) {
        var address = AddressFactory.build("13", "Complex Name", "123", "streetName", 1234, city);
        var employeeAddress = EmployeeAddressFactory.build(employee.getStaffID(), address);

        this.service.save(this.employee);
        this.countryService.save(country);
        this.cityService.save(city);
        this.addressService.save(employeeAddress);
    }

    @Test
    @Order(7)
    void delete() {
        String url = baseUrl + "delete";
        this.restTemplate.delete(url);
    }

    @Test
    @Order(5)
    void deleteById() {
        String url = baseUrl + "delete/" + this.employee.getStaffID();
        this.restTemplate.delete(url);
    }

    @Test
    @Order(6)
    void findAll() {
        String url = baseUrl + "all";
        ResponseEntity<Employee[]> response = this.restTemplate.getForEntity(url, Employee[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(response.getBody().length == 0)
        );
    }

}