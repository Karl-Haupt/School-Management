package za.ac.cput.service.location.impl;
/*
 This is the city service implementation
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.location.City;
import za.ac.cput.repository.location.CityRepository;
import za.ac.cput.service.location.ICityService;

import java.util.List;
import java.util.Optional;
@Service
public class CityServiceImpl implements ICityService {
    private CityRepository cityRepository;

    @Autowired
    private CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    @Override
    public City save(City city) {
        return this.cityRepository.save(city);
    }

    @Override
    public Optional<City> read(String id) {
        return this.cityRepository.findById(id);
    }

    @Override
    public void delete(City city) {
        this.cityRepository.delete(city);
    }

    @Override
    public List<City> findByCountryID(String id) {
        return null;
    }

    @Override
    public List<City> findAllCities() {
        return this.cityRepository.findAll();
    }
}
