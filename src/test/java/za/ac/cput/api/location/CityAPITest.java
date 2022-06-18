package za.ac.cput.api.location;
/*
 This is the city api test
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import za.ac.cput.controller.location.CountryController;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.service.location.impl.CityServiceImpl;
import za.ac.cput.service.location.impl.CountryServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CityAPITest {
    @Autowired
    private CityServiceImpl cityService;
    @Autowired
    private CountryServiceImpl countryService;

    @Autowired
    private CityAPI cityAPI;

    private Country country;
    private City city;
    @BeforeEach
    public void startUp(){
        this.country = CountryFactory.buildCountry("RSA", "South Africa");
        this.city = CityFactory.buildCity("CPT", "Cape Town",country);

    }
    @Test
    void save() {
        AddCountryToDB();
        City saved = this.cityAPI.save(this.city);
        System.out.println(this.cityService.read(this.city.getId()));
        assertNotNull(saved);
    }
    void AddCountryToDB(){
        var country = CountryFactory.buildCountry("RSA", "South Africa");
        this.countryService.save(country);
    }
}