package za.ac.cput.service.location.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.location.City;
import za.ac.cput.repository.location.ICityRepository;
import za.ac.cput.service.location.ICityService;

import java.util.List;
import java.util.Optional;
@Service
public class CityServiceImpl implements ICityService {
    private ICityRepository cityRepository;

    @Autowired
    private CityServiceImpl(ICityRepository cityRepository) {
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
    public List<City> findAllCities() {
        return this.cityRepository.findAll();
    }
}
