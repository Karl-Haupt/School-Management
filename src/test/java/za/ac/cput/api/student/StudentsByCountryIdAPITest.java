package za.ac.cput.api.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.repository.location.CityRepository;
import za.ac.cput.repository.student.StudentAddressRepository;
import za.ac.cput.service.student.StudentService;

import static org.junit.jupiter.api.Assertions.*;

class StudentsByCountryIdAPITest
{
    private StudentService studentService;
    private StudentAddressRepository studentAddressRepository;
    private CityRepository cityRepository;
    StudentsByCountryIdAPI studentsByCountryIdAPI;
    @BeforeEach
    void setUp()
    {
         //studentsByCountryIdAPI =
              //  new StudentsByCountryIdAPI(studentService, studentAddressRepository, cityRepository);
    }

    @Test
    void getStudentSurnamesByCountry()
    {
        //boolean firstOne = studentsByCountryIdAPI.getStudentSurnamesByCountry("Count6");
        //System.out.println(firstOne);

    }
}