package za.ac.cput.service.location.impl;

/* CountryServiceImplTest.java
   Testing the Service Implementation for Country
   Author: Joshua Daniel Jonkers(215162668)
   Date: 14/06/2022
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.location.Country;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.repository.location.CountryRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryServiceImplTest {

    private Country country;
    @Autowired
    private CountryRepository repository;
    @Autowired
    private CountryServiceImpl service;

    @BeforeEach
    void setUp() {
        this.country = CountryFactory.buildCountry("ZA", "South Africa");
        Country saved = this.service.save(this.country);
        assertEquals(this.country, saved);
    }

    @AfterEach
    void tearDown() {
        this.service.delete(this.country);
        List<Country> countryList = this.service.findAll();
        assertEquals(0, countryList.size());
    }

    @Test
    void save() {
        Country saveCountry = this.service.save(this.country);
        assertEquals(this.country, saveCountry);
    }

    @Test
    void read() {
        Optional<Country> read = this.service.read(country.getCountryID());
        assertAll(
                () -> assertTrue(read.isPresent()),
                () -> assertEquals(country, read.get())
        );
    }

    @Test
    void delete() {
        this.service.delete(this.country);
        this.service.findAll();
    }

    @Test
    void deleteByID() {
        this.service.deleteByID(country.getCountryID());
        List<Country> countryList = this.service.findAll();
        assertAll(
                () -> assertEquals(0, countryList.size())
        );
    }
}