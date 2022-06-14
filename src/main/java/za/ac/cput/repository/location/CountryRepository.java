package za.ac.cput.repository.location;

/* CountryRepository.java
   Repository for the Country
   Author: Joshua Daniel Jonkers(215162668)
   Date: 11/06/2022
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.location.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
