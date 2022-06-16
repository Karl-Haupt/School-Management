package za.ac.cput.repository.student;
/*
 This is the student address repository interface class
 Mponeng Ratego
 216178991
*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.ac.cput.domain.student.StudentAddress;

@Repository
public interface StudentAddressRepository extends JpaRepository<StudentAddress,String>{


}
