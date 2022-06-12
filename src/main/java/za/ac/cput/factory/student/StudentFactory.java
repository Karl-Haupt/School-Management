/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.factory.student;

import za.ac.cput.domain.lookup.Name;
import za.ac.cput.factory.lookup.student.Student;
import za.ac.cput.helper.Validator;

public class StudentFactory
{

    public static Student buildStudent(String id, String email, Name name)
    {
        Validator.checkStringParam("Student ID", id);
        Validator.isValidEmail("Student Email", email);
        Validator.isNull("Student Name", name);

        return new Student.Builder().setStudentId(id).setName(name).setEmail(email).build();
    }
}
