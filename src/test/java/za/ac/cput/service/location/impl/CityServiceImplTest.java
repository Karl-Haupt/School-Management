package za.ac.cput.service.location.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.repository.location.CityRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityServiceImplTest {
    private City city;

    private Country country;

    @Autowired
    private CityServiceImpl cityService;

    @Autowired
    private CountryServiceImpl countryService;

    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    public void startUp(){
        this.country = CountryFactory.buildCountry("2-SA","South America");
        this.city = CityFactory.buildCity("2-AMT","American Town",country);
    }

    @Test
    @Order(1)
    void save() {
        AddCountryToDB();
        City saveCity = this.cityService.save(city);
        assertEquals(this.city, saveCity);
    }

    @Test
    @Order(2)
    void read() {
        Optional<City> readCity = this.cityService.read(city.getId());
        assertAll(
                () -> assertTrue(readCity.isPresent()),
                () -> assertEquals(city, readCity.get())
        );
    }

    @Test
    @Order(5)
    void delete() {
        this.cityService.delete(this.city);
        findAll();
    }


    void findAll(){
        List<City> cityList = this.cityService.findAllCities();
        assertEquals(0, cityList.size());
    }
    @Test
    @Order(4)
    void deleteCityByID() {
        this.cityService.deleteCityByID(city.getId());
        findAll();
    }

    @Test
    @Order(3)
    void findCityByCountryID() {
        AddCountryToDB();
        var cityByCountry = this.cityService.findCityByCountryID("2-SA");
        assertAll(
                () -> assertNotNull(cityByCountry),
                () -> assertEquals(1, cityByCountry.size())
        );
    }
   void AddCountryToDB(){
    var country = CountryFactory.buildCountry("2-SA","South America");
    this.countryService.save(country);
    }
}