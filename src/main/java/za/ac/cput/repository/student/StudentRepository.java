/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.student.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>
{
    @Query (value = "SELECT student.student_id, student.last_name  FROM student", nativeQuery = true)
    List<String[]> readIdAndSurnames(); //For Q8. Part 1 of 3

}
