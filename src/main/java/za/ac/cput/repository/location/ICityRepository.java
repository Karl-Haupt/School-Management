package za.ac.cput.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.location.City;
@Repository
public interface ICityRepository extends JpaRepository<City,String> {


}
