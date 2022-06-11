package za.ac.cput.service.employee;

/*
 EmployeeService.java -> Provides domain-specific methods for EmployeeServiceImpl
 Student Name: Karl Haupt
 Student Number: 220236585
*/

import za.ac.cput.domain.employee.Employee;
import za.ac.cput.service.IService;

import java.util.List;

public interface EmployeeService extends IService<Employee, String> {
    void deleteById(String id);
    List<Employee> findAll();
}
