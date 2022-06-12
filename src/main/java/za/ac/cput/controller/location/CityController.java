package za.ac.cput.controller.location;
/*
 This is the city controller
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.location.City;
import za.ac.cput.service.location.impl.CityServiceImpl;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/school-management/city/")
public class CityController {
    private final CityServiceImpl cityService;
    @Autowired
    public CityController(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @PostMapping("save")
    public City save(City city) {
        return cityService.save(city);
    }
    @GetMapping("read/{id}")
    public Optional<City> read(@PathVariable String id) {
        return this.cityService.read(id);
    }
    @DeleteMapping("delete")
    public void delete(City city) {
        this.cityService.delete(city);
    }
    @GetMapping("all")
    public List<City> findAllCities() {
        return this.cityService.findAllCities();
    }
    @GetMapping("read/{countryID}")
    public Optional<City> findCityByCountryID(@PathVariable String countryID) {
        return this.cityService.findCityByCountryID(countryID);
    }
}
