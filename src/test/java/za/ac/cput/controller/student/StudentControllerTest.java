/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.controller.student;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.controller.location.CityController;
import za.ac.cput.controller.location.CountryController;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.domain.lookup.Address;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.domain.student.Student;
import za.ac.cput.domain.student.StudentAddress;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.factory.lookup.AddressFactory;
import za.ac.cput.factory.lookup.NameFactory;
import za.ac.cput.factory.student.StudentAddressFactory;
import za.ac.cput.factory.student.StudentFactory;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentControllerTest
{
    @LocalServerPort private int port;
    @Autowired private StudentController controller;
    @Autowired private StudentAddressController addressController;
    @Autowired private CountryController countryController;
    @Autowired private CityController cityController;

    @Autowired private TestRestTemplate restTemplate;

    private Student student;
    private String baseUrl;
    private Name name;
    private Country country;
    private City city;
    private Address address;
    private StudentAddress studentAddress;


    @BeforeEach
    void setUp()
    {
        assertNotNull(controller);
        this.name = NameFactory.buildName("Chandler", "Joey", "Ross");
        this.student = StudentFactory.buildStudent("ST001", "ST001@mycput.ac.za", name);
        this.country = CountryFactory.buildCountry("111", "Angola");
        this.city = CityFactory.buildCity("CITY1", "Luanda", country);
        this.address =
                AddressFactory.build
                            ("1","Rose Gardens", "12","Sobukwe Road",3000,city);
        this.studentAddress = StudentAddressFactory.build(this.student.getStudentId(), address);
        this.baseUrl = "http://localhost:" + this.port + "/school-management/student/";
    }

    @Test
    @Order(5)
    void readSurnameWithCountryId() {
        String urlReadSurname = baseUrl + "readsurname/" + country.getCountryID();
        this.controller.save(this.student);
        this.countryController.save(this.country);
        this.cityController.save(this.city);
        this.addressController.save(this.studentAddress);

        ResponseEntity<String[]> studentSurnames = this.restTemplate.getForEntity(urlReadSurname, String[].class);
        for (var n : studentSurnames.getBody()) {
            System.out.println("Surname" + n);
        }
        assertNotNull(studentSurnames.getBody());
    }


    @Test
    @Order(1)
    void save()
    {
        String url = baseUrl + "save";
        ResponseEntity<Student> response = this.restTemplate.postForEntity(url, this.student, Student.class);
        assertAll(
                ()-> assertEquals(HttpStatus.OK, response.getStatusCode()),
                ()->assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(2)
    void readById()
    {
        String url = baseUrl + "read/" + this.student.getStudentId();
        ResponseEntity<Student> response = this.restTemplate.getForEntity(url, Student.class);
        assertAll(
                ()->assertEquals(HttpStatus.OK, response.getStatusCode()),
                ()->assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(4)
    void readAll()
    {
        String url = baseUrl + "readAll";
        ResponseEntity<Student[]> response = this.restTemplate.getForEntity(url, Student[].class);

        assertAll(
                ()->assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->assertTrue(response.getBody().length == 0)
                );
    }

    @Test
    @Order(3)
    void deleteById()
    {
        String url = baseUrl + "deleteById/" + this.student.getStudentId();
        this.restTemplate.delete(url);
    }
}