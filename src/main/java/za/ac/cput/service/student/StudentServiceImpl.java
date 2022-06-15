/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.service.student;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.domain.student.Student;
import za.ac.cput.repository.student.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService
{
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student)
    {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> read(String s)
    {
        return studentRepository.findById(s);
    }

    @Override
    public void delete(Student student)
    {
        studentRepository.delete(student);
    }

    @Override
    public void deleteById(String studentId)
    {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<Student> readAll()
    {
        return studentRepository.findAll();
    }


    /*
    @Override
    public List<String> AllStudentId()
    {
        List<Student> allStudents = readAll();
        List<String> allIds = allStudents.stream().map(Student::getStudentId).collect(Collectors.toList());

        return allIds;
    }
     */
}
