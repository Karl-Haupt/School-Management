package za.ac.cput.service.employee.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.employee.EmployeeAddress;
import za.ac.cput.repository.employee.EmployeeAddressRepository;
import za.ac.cput.service.employee.EmployeeAddressService;
import java.util.List;
import java.util.Optional;

/*
 EmployeeAddressServiceImpl.java
 Student Name: Mike Somelezo Tyolani
 Student Number: 220187568
*/
@Service
public class EmployeeAddressServiceImpl implements EmployeeAddressService {
    private EmployeeAddressRepository repository;

    @Autowired
    public EmployeeAddressServiceImpl(EmployeeAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeAddress save(EmployeeAddress employeeAddress) {
        return this.repository.save(employeeAddress);
    }

    @Override
    public Optional<EmployeeAddress> read(String ID) {
        return this.repository.findById(ID);
    }

    @Override
    public void delete(EmployeeAddress employeeAddress) {
        this.repository.delete(employeeAddress);
    }

    @Override
    public void deleteById(String Id) {
        this.repository.deleteById(Id);
    }

    @Override
    public List<EmployeeAddress> findAll() {
        return this.repository.findAll();
    }

    public Optional<EmployeeAddress> findEmployeeAddressByStaffId(String staffId) {
        return this.repository.findEmployeeAddressByStaffId(staffId);

    }
}