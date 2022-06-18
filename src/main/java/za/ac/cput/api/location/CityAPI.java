package za.ac.cput.api.location;
/*
 This is the city API
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.location.City;
import za.ac.cput.service.location.impl.CityServiceImpl;
import za.ac.cput.service.location.impl.CountryServiceImpl;

@Component
public class CityAPI {
    private CityServiceImpl cityService;
    private CountryServiceImpl countryService;

    @Autowired
    public CityAPI( CountryServiceImpl countryService, CityServiceImpl cityService) {
        this.countryService =countryService;
        this.cityService = cityService;

    }

    public City save(City city) {
        this.countryService.read(city.getCountry().getCountryID()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));

        return this.cityService.save(city);
    }
}
