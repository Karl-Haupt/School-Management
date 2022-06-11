package za.ac.cput.service.location;


import za.ac.cput.domain.location.City;
import za.ac.cput.service.IService;

import java.util.List;

public interface ICityService extends IService<City, String> {

    List<City> findAllCities();
}
