package za.ac.cput.repository.employee;

/*
 EmployeeRepository.java -> Provides database queries for the Employee entity
 Student Name: Karl Haupt
 Student Number: 220236585
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.employee.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
//    @Query(value = "SELECT * FROM employee WHERE employee.email = ?1", nativeQuery = true)
    @Query(value = "SELECT E.name.firstName FROM Employee E WHERE E.email = :email")
    Optional<String> findEmployeeByEmail(String email);
    @Query(value = "SELECT E.name.firstName FROM Employee E INNER JOIN EmployeeAddress A ON E.staffID = A.staffId WHERE A.address.city.id = :cityID")
    List<String> findEmployeesByCity(@Param("cityID") String cityID);

}
