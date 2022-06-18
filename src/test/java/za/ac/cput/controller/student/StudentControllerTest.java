/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.controller.student;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.domain.student.Student;
import za.ac.cput.factory.lookup.NameFactory;
import za.ac.cput.factory.student.StudentFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentControllerTest
{
    @LocalServerPort private int port;
    @Autowired private StudentController controller;
    @Autowired private TestRestTemplate restTemplate;

    private Student student;
    private String baseUrl;

    @BeforeEach
    void setUp()
    {
        assertNotNull(controller);
        Name name = NameFactory.buildName("Chandler", "Joey", "Ross");
        this.student = StudentFactory.buildStudent("ST001", "ST001@mycput.ac.za", name);
        this.baseUrl = "http://localhost:" + this.port + "/school-management/student/";
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