package za.ac.cput.service.student.impl;
/*
 This is the student address implementation
 Mponeng Ratego
 216178991
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.student.StudentAddress;
import za.ac.cput.repository.student.StudentAddressRepository;
import za.ac.cput.service.student.StudentAddressService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAddressImp implements StudentAddressService {

    private StudentAddressRepository repo;

    @Autowired
    private StudentAddressImp(StudentAddressRepository repo) {
        this.repo = repo;
    }

    @Override
    public StudentAddress save(StudentAddress studentAddress) {
        return this.repo.save(studentAddress);
    }


    @Override
    public Optional<StudentAddress> read(String studentId) {
        return this.repo.findById(studentId);
    }

    @Override
    public void delete(StudentAddress studentAddress){

        this.repo.delete(studentAddress);
    }

    @Override
    public void deleteById(String studentId) {
        this.repo.deleteById(studentId);
    }

    @Override
    public List<StudentAddress> findAll() {
        return this.repo.findAll();
    }
}
