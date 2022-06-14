package za.ac.cput.repository.location;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityRepositoryTest {
    private Country country = CountryFactory.buildCountry("1-RSA","South Africa");
    private City city = CityFactory.buildCity("1-CPT","Cape Town",country);
    @Autowired
    private CityRepository cityRepository;

    @Test
    void findCityByCountryID() {
        Optional<City> findByCoutnryID = this.cityRepository.findCityByCountryID(this.city.getCountry().getCountryID());
        assertAll(
                () -> assertTrue(findByCoutnryID.isPresent()),
                () -> assertEquals(this.city, findByCoutnryID.get())
        );
    }
}