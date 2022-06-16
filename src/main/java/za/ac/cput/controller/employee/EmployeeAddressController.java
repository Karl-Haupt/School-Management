package za.ac.cput.controller.employee;

/*
 EmployeeAddressController.java domain
 Student Name: Mike Somelezo Tyolani
 Student Number: 220187568
*/

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.employee.EmployeeAddress;
import za.ac.cput.factory.employee.EmployeeAddressFactory;
import za.ac.cput.service.employee.impl.EmployeeAddressServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/school-management/employee-address/")
@Slf4j
public class EmployeeAddressController {
    private final EmployeeAddressServiceImpl employeeAddressService;

    @Autowired
    public EmployeeAddressController(EmployeeAddressServiceImpl employeeAddressService) {
        this.employeeAddressService = employeeAddressService;
    }

    @PostMapping("save")
    public ResponseEntity<EmployeeAddress> save(@Valid @RequestBody EmployeeAddress employeeAddress) {
        log.info("Request to save: {}", employeeAddress);
        EmployeeAddress newEmployeeAddress = EmployeeAddressFactory.build(employeeAddress.getStaffId(),employeeAddress.getAddress());
        EmployeeAddress employeeAddressSaved = this.employeeAddressService.save(newEmployeeAddress);
        return ResponseEntity.ok(employeeAddressSaved);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<EmployeeAddress> read(@PathVariable String id) {
        EmployeeAddress readEmployeeAddress = this.employeeAddressService.read(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Address not found"));
        return ResponseEntity.ok(readEmployeeAddress);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(EmployeeAddress employeeAddress) {
        log.info("Request to delete: {}", employeeAddress);
        this.employeeAddressService.delete(employeeAddress);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        log.info("Request to delete by id: {}", id);
        this.employeeAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<EmployeeAddress>> findAll() {
        List<EmployeeAddress> findAllEmployeeAddressList = this.employeeAddressService.findAll();
        return ResponseEntity.ok(findAllEmployeeAddressList);
    }

}
