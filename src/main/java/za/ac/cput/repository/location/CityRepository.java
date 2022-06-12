package za.ac.cput.repository.location;
/*
 This is the city repo interface
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.location.City;
@Repository
public interface CityRepository extends JpaRepository<City,String> {


}
