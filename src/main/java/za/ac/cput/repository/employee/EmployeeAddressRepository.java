package za.ac.cput.repository.employee;

/*
 EmployeeAddressRepository.java
 Student Name: Mike Somelezo Tyolani
 Student Number: 220187568
*/


import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.employee.EmployeeAddress;

import java.util.Optional;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, String> {
    Optional<EmployeeAddress> findEmployeeAddressByStaffId(String staffId);

}
