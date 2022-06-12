/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.factory.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.factory.lookup.student.Student;
import za.ac.cput.factory.lookup.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest
{

    private Student student;
    private Name name;

    @BeforeEach
    void setUp()
    {
        name = NameFactory.buildName("Huey", "Percy", "Newton");
        student = StudentFactory.buildStudent("ST1234", "hpnewton@bpanther.com", name);
    }

    @Test
    void testStudentCreation()
    {
        assertAll(
                ()-> assertSame(student.getClass(),Student.class),
                ()-> assertNotNull(name),
                ()-> assertNotNull(student));
    }

    @Test
    void testNullId()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StudentFactory.buildStudent("", "hpnewton@bpanther.com", name));
        String receivedMessage = exception.getMessage();
        String expectedMessage = "Invalid value for params: Student ID";

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    void testNullName()
    {
        name = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                StudentFactory.buildStudent("ST1234", "hpnewton@bpanther.com", name));
        String returned = exception.getMessage();
        String expected = "Invalid value for params: Student Name";

        assertEquals(expected, returned);
    }

    @Test
    void testInvalidEmail()
    {
        String expected = "Invalid value for params: Student Email";
        String returned = null;
        try
        {
           StudentFactory.buildStudent("ST1234", "hpnewtonbpanther.com", name);
        }
        catch (Exception e)
        {
            System.out.println(e);
            returned = e.getMessage();
        }
        assertEquals(expected, returned);

    }

}