package za.ac.cput.repository.location;
/*
 This is the city repo interface
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.location.City;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,String> {

    //SQL COMMAND COMES HERE
    @Query(value = "SELECT c.name FROM city c WHERE c.FK_countryID = ?1 ORDER BY name ASC", nativeQuery = true)
    List<String> findCityByCountryID(@Param("countryID") String id);
}
