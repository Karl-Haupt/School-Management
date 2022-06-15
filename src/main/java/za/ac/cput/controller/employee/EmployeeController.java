package za.ac.cput.controller.employee;

/*
 EmployeeController.java -> Exposes the web services for employees
 Student Name: Karl Haupt
 Student Number: 220236585
*/

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.employee.Employee;
import za.ac.cput.factory.employee.EmployeeFactory;
import za.ac.cput.service.employee.impl.EmployeeServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/school-management/employee/")
@Slf4j
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @Autowired public EmployeeController(EmployeeServiceImpl service) {
        this.employeeService = service;
    }

    @PostMapping("save")
    public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee) {
        Employee emp = EmployeeFactory.buildEmployee(employee.getStaffID(), employee.getEmail(), employee.getName());
        return ResponseEntity.ok(employeeService.save(emp));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Employee> read(@PathVariable String id) {
        var emp = this.employeeService.read(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));
        return  ResponseEntity.ok(emp);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(Employee employee) {
        log.info("Delete request: {} ", employee);
        this.employeeService.delete(employee);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        this.employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employeeList = this.employeeService.findAll();
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("read")
    public ResponseEntity<Employee> findByEmail(@RequestParam("email") String email) {
        var emp = this.employeeService.findEmployeeByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));
        return ResponseEntity.ok(emp);
    }

    @GetMapping("read")
    public ResponseEntity<List<Employee>> findEmployeesByCity(@RequestParam("cityId") String cityId) {
        List<Employee> employeeList = this.employeeService.findEmployeesByCity(cityId);
        return ResponseEntity.ok(employeeList);
    }

}
