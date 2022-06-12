/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.factory.lookup.student.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>
{
    public List<Student> findByStudentId (String studentId);
}
