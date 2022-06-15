package za.ac.cput.repository.employee;

/*
 EmployeeRepository.java -> Provides database queries for the Employee entity
 Student Name: Karl Haupt
 Student Number: 220236585
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.employee.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query(value = "SELECT employeee.first_name FROM employee WHERE employee.email = ?1", nativeQuery = true)
    Optional<Employee> findEmployeeByEmail(String email);

    @Query(value = "SELECT employee.first_name FROM employee INNER JOIN employee_address ON employee.staffid = employee_address.staff_id WHERE employee_address.city_id = ?1;", nativeQuery = true)
    List<Employee> findEmployeesByCity(String cityID);
}
