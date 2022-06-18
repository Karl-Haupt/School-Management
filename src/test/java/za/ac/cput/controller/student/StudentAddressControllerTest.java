package za.ac.cput.controller.student;

/*
 This is the student address controller implementation test cases
  Mponeng Ratego
  216178991
*/

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.domain.lookup.Address;
import za.ac.cput.domain.student.StudentAddress;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.factory.lookup.AddressFactory;
import za.ac.cput.factory.student.StudentAddressFactory;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentAddressControllerTest {

    @LocalServerPort
    private int studentAddressPort;
    @Autowired
    private StudentAddressController studentAddressController;
    @Autowired
    private TestRestTemplate studentAddressRestTemp;

    private StudentAddress studentAddress;




    private City city;

    private Country country;

    private Address address;

    private  String studentAddressBaseUrl;

    @BeforeEach
    public void startUp(){
        assertNotNull(studentAddressController);
        Address addresses = AddressFactory.build("unitNumber", "complexName", "streetNumber", "streetName", 7570, city);
        this.city = CityFactory.buildCity("100-PE"," Port Elizabeth",country);
        this.country = CountryFactory.buildCountry("countryID","countryName");
        this.studentAddress = StudentAddressFactory.build("studentId",address);
        this.studentAddressBaseUrl = "http://localhost:" + this.studentAddressPort + "/school-management/studentAddress/";

    }


    @Test
    @Order(1)
    void save() {
        String studentAddressUrl = studentAddressBaseUrl + "save";
        ResponseEntity<StudentAddress> saveResponse = this.studentAddressRestTemp.
                postForEntity(studentAddressUrl,this.studentAddress, StudentAddress.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, saveResponse.getStatusCode()),
                () -> assertNotEquals(null,saveResponse)
        );
    }

    @Test
    @Order(2)
    void read() {
        String readUrl = studentAddressBaseUrl + "read/" + this.city.getId();
        ResponseEntity<StudentAddress> readResponse = this.studentAddressRestTemp.getForEntity(readUrl,StudentAddress.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, readResponse.getStatusCode()),
                () -> assertNotEquals(null,readResponse)
        );
    }

    @Test
    @Order(5)
    void findAll() {
        String findAllUrl = studentAddressBaseUrl +"findAll";
        ResponseEntity<StudentAddress[]> findAllResponse = this.studentAddressRestTemp.
                getForEntity(findAllUrl,StudentAddress[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, findAllResponse.getStatusCode()),
                () -> assertNotNull(findAllResponse.getBody()),
                () -> assertTrue(findAllResponse.getBody().length == 0)

        );
    }

    @Test
    @Order(4)
    void delete() {
        String deleteUrl = studentAddressBaseUrl + "delete/";
        this.studentAddressRestTemp.delete(deleteUrl);
    }

    @Test
    @Order(3)
    void deleteById() {
        String deleteByIdUrl = studentAddressBaseUrl + "delete/" + this.studentAddress.getStudentId();
        this.studentAddressRestTemp.delete(deleteByIdUrl);
    }
}