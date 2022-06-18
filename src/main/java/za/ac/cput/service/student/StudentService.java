/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.service.student;

import za.ac.cput.domain.student.Student;
import za.ac.cput.service.IService;

import java.util.List;

public interface StudentService extends IService<Student, String>
{
    public List<Student> readAll();

    public void deleteById(String studentId);
    List<String> getStudentSurnameByCountryId(String countryId);

}
