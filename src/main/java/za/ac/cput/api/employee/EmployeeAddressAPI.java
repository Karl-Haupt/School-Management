package za.ac.cput.api.employee;

/*
 EmployeeAddressApi.java domain
 Student Name: Mike Somelezo Tyolani
 Student Number: 220187568
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.employee.EmployeeAddress;
import za.ac.cput.domain.location.City;
import za.ac.cput.service.employee.EmployeeAddressService;
import za.ac.cput.service.employee.EmployeeService;
import za.ac.cput.service.location.ICityService;

@Component
public class EmployeeAddressAPI {
    private final EmployeeService employeeService;
    private final ICityService iCityService;
    private final EmployeeAddressService employeeAddressService;
    private City city;

    @Autowired
    public EmployeeAddressAPI (EmployeeService employeeService,ICityService iCityService, EmployeeAddressService employeeAddressService ){
        this.employeeService = employeeService;
        this.iCityService = iCityService;
        this.employeeAddressService = employeeAddressService;
    }

    public EmployeeAddress save(EmployeeAddress employeeAddress) {
        this.employeeService.read(employeeAddress.getStaffId())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));
        this.iCityService.read(city.getId())
                .orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "City Not Found"));
                return this.employeeAddressService.save(employeeAddress);
    }
}
