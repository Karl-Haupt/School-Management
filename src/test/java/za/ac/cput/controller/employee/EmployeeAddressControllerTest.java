package za.ac.cput.controller.employee;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.employee.EmployeeAddress;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.domain.lookup.Address;
import za.ac.cput.factory.employee.EmployeeAddressFactory;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.factory.lookup.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeAddressControllerTest {
    @LocalServerPort
    private int employeeAddressPort;

    @Autowired
    private EmployeeAddressController employeeAddressController;

    @Autowired
    private TestRestTemplate employeeAddressRestTemp;

    private EmployeeAddress employeeAddress;
    private Country country;
    private City city;
    private  String employeeAddressBaseUrl;

    @BeforeEach
    public void startUp(){
        assertNotNull(employeeAddressController);
        Address addresses = AddressFactory.build("unit-number", "complex-name", "street-number", "street-name", 1234, city);
        this.city = CityFactory.buildCity("1-CPT"," Cape Town",country);
        this.country = CountryFactory.buildCountry("country-id","country-name");
        this.employeeAddress = EmployeeAddressFactory.build("staff-id",addresses);
        this.employeeAddressBaseUrl = "http://localhost:" + this.employeeAddressPort + "/api/v1/school-management/employee-address/";

    }
    @Test
    @Order(1)
    void save() {
        String saveUrl = employeeAddressBaseUrl + "save";
        ResponseEntity<EmployeeAddress> saveResponse = this.employeeAddressRestTemp.
                postForEntity(saveUrl,this.employeeAddress, EmployeeAddress.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, saveResponse.getStatusCode()),
                () -> assertNotEquals(null,saveResponse)
        );
    }

    @Test
    @Order(2)
    void read() {
        String employeeAddressUrl = employeeAddressBaseUrl + "read/" + this.city.getId();
        ResponseEntity<EmployeeAddress> readResponse = this.employeeAddressRestTemp.getForEntity(employeeAddressUrl,EmployeeAddress.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, readResponse.getStatusCode()),
                () -> assertNotEquals(null,readResponse)
        );
    }


    @Test
    @Order(3)
    void deleteById() {
        String deleteByIdUrl = employeeAddressBaseUrl + "delete/" + this.employeeAddress.getStaffId();
        this.employeeAddressRestTemp.delete(deleteByIdUrl);
    }

    @Test
    @Order(4)
    void delete() {
        String deleteUrl = employeeAddressBaseUrl + "delete/";
        this.employeeAddressRestTemp.delete(deleteUrl);
    }

    @Test
    @Order(5)
    void findAll() {
        String findAllUrl = employeeAddressBaseUrl +"all";
        ResponseEntity<EmployeeAddress[]> findAllResponse = this.employeeAddressRestTemp.
                getForEntity(findAllUrl,EmployeeAddress[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, findAllResponse.getStatusCode()),
                () -> assertNotNull(findAllResponse.getBody()),
                () -> assertTrue(findAllResponse.getBody().length == 0)

        );
    }

}