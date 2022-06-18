/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.student.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>
{

    @Query (value = "SELECT S.name.lastName " +
            "FROM Student S " +
            "INNER JOIN StudentAddress A " +
            "ON A.studentId = S.studentId " +
            "INNER JOIN City C " +
            "ON A.address.city = C.id " +
            "WHERE C.country = :countryID")
    List<Student> getStudentSurnamesByCountryId(@Param("countryID")String countryID);

}
