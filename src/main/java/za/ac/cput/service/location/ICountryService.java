package za.ac.cput.service.location;

/* ICountryService.java
   Service Interface for Country
   Author: Joshua Daniel Jonkers(215162668)
   Date: 14/06/2022
 */

import za.ac.cput.domain.location.Country;
import za.ac.cput.service.IService;

import java.util.List;

public interface ICountryService extends IService<Country, String> {
    void deleteByID(String countryID);
    List<Country> findAll();
}
