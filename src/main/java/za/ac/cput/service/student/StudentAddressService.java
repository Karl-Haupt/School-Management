package za.ac.cput.service.student;
/*
 This is the student address service class
 Mponeng Ratego
 216178991
*/

import za.ac.cput.domain.student.StudentAddress;
import za.ac.cput.service.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentAddressService extends IService<StudentAddress, String> {


    void deleteById(String studentId);

    List<StudentAddress> findAll();
}
