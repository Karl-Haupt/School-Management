package za.ac.cput.service.employee.impl;

/*
 EmployeeServiceImpl.java -> Implementation of business logic and database queries
 Student Name: Karl Haupt
 Student Number: 220236585
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.employee.Employee;
import za.ac.cput.repository.employee.EmployeeRepository;
import za.ac.cput.service.employee.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository repository;

    @Autowired public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee save(Employee employee) {
        return this.repository.save(employee);
    }

    @Override
    public Optional<Employee> read(String ID) {
        return this.repository.findById(ID);
    }

    @Override
    public void delete(Employee employee) {
        this.repository.delete(employee);
    }

    @Override
    public void deleteById(String ID) {
        this.repository.deleteById(ID);
    }

    @Override
    public List<Employee> findAll() {
        return this.repository.findAll();
    }

    public Optional<Employee> findEmployeeByEmail(String email) {
        return this.repository.findEmployeeByEmail(email);
    }
}
