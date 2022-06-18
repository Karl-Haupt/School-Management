package za.ac.cput.controller.location;

/* CountryControllerTest.java
   Controller Test Cases for Country
   Author: Joshua Daniel Jonkers(215162668)
   Date: 16/06/2022
 */

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.location.Country;
import za.ac.cput.factory.location.CountryFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest {
    @LocalServerPort
    private int localPort;
    @Autowired
    private CountryController controller;
    @Autowired
    private TestRestTemplate restTemplate;

    private Country country;
    private String baseURL;

    @BeforeEach
    void setUp() {
        assertNotNull(controller);
        this.country = CountryFactory.buildCountry("ZA", "South Africa");
        this.baseURL = "http://localhost:" + this.localPort+ "/api/v1/school-management/country/";
        System.out.println(baseURL);
    }

    @Test
    @Order(1)
    void save() {
        String url = baseURL + "save";
        ResponseEntity<Country> response = this.restTemplate.
                postForEntity(url,this.country, Country.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotEquals(null,response)
        );
    }

    @Test
    @Order(2)
    void read() {
        String url = baseURL + "read/" + this.country.getCountryID();
        ResponseEntity<Country> response = this.restTemplate.getForEntity(url,Country.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotEquals(null,response)
        );
    }

    @Test
    @Order(3)
    void delete() {
        String url = baseURL + "delete/";
        this.restTemplate.delete(url);
    }

    @Test
    @Order(4)
    void deleteById() {
        String url = baseURL + "delete/" + this.country.getCountryID();
        this.restTemplate.delete(url);
    }

    @Test
    @Order(5)
    void findAll() {
        String url = baseURL +"all";
        ResponseEntity<Country[]> response = this.restTemplate.getForEntity(url,Country[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }
}