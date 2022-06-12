/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.factory.lookup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.lookup.Name;

import static org.junit.jupiter.api.Assertions.*;

class NameFactoryTest
{
    private Name name;

    @BeforeEach
    void setUp()
    {
        name = NameFactory.buildName("John", "Fitzgerald", "Kennedy");
    }

    @Test
    void testNameCreation()
    {
        assertAll(
                ()-> assertNotNull(name),
                ()-> assertSame(Name.class, name.getClass())
        );
    }

    @Test
    void testNullFirstName()
    {
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()->  NameFactory.buildName("", "Fitzgerald", "Kennedy"));
        String expectedMessage = "Invalid value for params: First Name";
        String returnedMessage = exception.getMessage();
        assertEquals(expectedMessage, returnedMessage);
    }

    @Test
    void testEmptyMiddleName()  //Middle name is not mandatory, but must not be null
    {
        name = NameFactory.buildName("John", " ", "Kennedy");
        System.out.println(name);
        assertNotNull(name);
    }

    @Test
    void testNullMiddleName() //if middle name is null, exception is thrown
    {
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> NameFactory.buildName("John", "", "Kennedy"));
        String expectedMessage = "If theres is no middle name, add space instead.";
        String returnedMessage = exception.getMessage();
        assertEquals(expectedMessage, returnedMessage);

    }

    @Test
    void testNullLastName()
    {
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()->  NameFactory.buildName("John", "Fitzgerald", ""));
        String expectedMessage = "Invalid value for params: Last Name";
        String returnedMessage = exception.getMessage();
        assertEquals(expectedMessage, returnedMessage);
    }

}