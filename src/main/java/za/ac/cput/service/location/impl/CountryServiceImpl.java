package za.ac.cput.service.location.impl;

/* CountryServiceImpl.java
   Service Implementation for Country
   Author: Joshua Daniel Jonkers(215162668)
   Date: 14/06/2022
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.location.Country;
import za.ac.cput.repository.location.CountryRepository;
import za.ac.cput.service.location.ICountryService;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements ICountryService {
    private CountryRepository repository;

    @Autowired
    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Country save(Country country) {
        return this.repository.save(country);
    }

    @Override
    public Optional<Country> read(String countryID) {
        return this.repository.findById(countryID);
    }

    @Override
    public void delete(Country country) {
        this.repository.delete(country);
    }

    @Override
    public void deleteByID(String countryID) {
        this.repository.deleteById(countryID);
    }

    @Override
    public List<Country> findAll() {
        return this.repository.findAll();
    }
}
