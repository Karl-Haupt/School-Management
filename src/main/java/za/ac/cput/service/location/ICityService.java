package za.ac.cput.service.location;
/*
 This is the city service interface
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/

import za.ac.cput.domain.location.City;
import za.ac.cput.service.IService;

import java.util.List;

public interface ICityService extends IService<City, String> {
    List<City> findByCountryID(String id);
    List<City> findAllCities();
}
