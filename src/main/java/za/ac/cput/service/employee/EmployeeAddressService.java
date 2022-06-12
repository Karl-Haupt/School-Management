package za.ac.cput.service.employee;

/*
 EmployeeAddressService.java
 Student Name: Mike Somelezo Tyolani
 Student Number: 220187568
*/

import za.ac.cput.domain.employee.EmployeeAddress;
import za.ac.cput.service.IService;

import java.util.List;

public interface EmployeeAddressService extends IService<EmployeeAddress, String> {
    void deleteById(String Id);
    List<EmployeeAddress> findAll();

}
