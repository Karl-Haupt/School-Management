package za.ac.cput.repository.location;
/*
 This is the city repo interface
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.location.City;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,String> {

    //SQL COMMAND COMES HERE
    @Query(value = "SELECT * FROM city WHERE city.FK_countryID = ?1", nativeQuery = true)
    Optional<City> findCityByCountryID(String id);
}
