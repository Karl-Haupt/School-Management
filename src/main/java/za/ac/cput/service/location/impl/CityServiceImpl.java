package za.ac.cput.service.location.impl;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.location.City;
import za.ac.cput.repository.location.CityRepository;
import za.ac.cput.repository.location.ICityRepository;
import za.ac.cput.service.location.ICityService;

import java.util.List;
import java.util.Optional;
@Service
public class CityServiceImpl implements ICityService {
    private ICityRepository cityRepository;

    private CityServiceImpl(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    @Override
    public City save(City city) {
        return null;
    }

    @Override
    public Optional<City> read(String s) {
        return Optional.empty();
    }

    @Override
    public void delete(City city) {

    }

    @Override
    public List<City> findAllCities() {
        return null;
    }
}
