/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.service.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.domain.student.Student;
import za.ac.cput.factory.lookup.NameFactory;
import za.ac.cput.factory.student.StudentFactory;
import za.ac.cput.repository.student.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest
{
    private Student student;
    private Student savedStudent;
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private StudentRepository repo;

    @BeforeEach
    void setUp()
    {
        Name name = NameFactory.buildName("Martin", "Luther", "King");
        this.student = StudentFactory.buildStudent("STU001", "stud1@mycput.ac.za", name);
        savedStudent = this.studentService.save(this.student);
    }

    @Test
    @Order(1)
    void save()
    {
        assertNotNull(savedStudent);
    }

    @Test
    @Order(2)
    void read()
    {
        Optional<Student> readStudent = studentService.read(this.student.getStudentId());
        System.out.println(readStudent);
        assertAll(
                ()-> assertTrue(readStudent.isPresent()),
                ()-> assertEquals(readStudent.get(), this.student)
        );
    }

    @Test
    @Order(3)
    void deleteById()
    {
        Optional<Student> readStudentBefore = studentService.read(this.student.getStudentId());
        assertTrue(readStudentBefore.isPresent());
        System.out.println("Object read before deletion: " + readStudentBefore);

        studentService.deleteById(this.student.getStudentId());
        Optional<Student> readStudentAfter = studentService.read(this.student.getStudentId());

        assertFalse(readStudentAfter.isPresent());
    }
}