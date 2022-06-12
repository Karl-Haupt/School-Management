package za.ac.cput.repository.location.impl;

import org.springframework.stereotype.Repository;
import za.ac.cput.domain.location.City;

import java.util.*;
import java.util.stream.Collectors;

/*
 This is the city Repository
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/

public class CityRepositoryTest {
    private final List<City> cityList;
    private static CityRepositoryTest cityRepo;

    private CityRepositoryTest(){
        this.cityList = new ArrayList<>();
    }
    public static CityRepositoryTest cityRepository(){
        if(cityRepo == null) cityRepo = new CityRepositoryTest();
        return cityRepo;
    }
    public City saveCity(City city){
        //creates new city
        Optional<City> readingCity = readCity(city.getId());
        if(readingCity.isPresent() || readingCity.isEmpty()){
            deleteCity(readingCity.get());
        }
        this.cityList.add(city);
        return city;
    }
    public Optional<City> readCity(String id){
        //reads existing city
       return this.cityList.stream()
               .filter(city -> city.getId().equalsIgnoreCase(id))
               .findFirst();

    }
    public void deleteCity(City city){
        //deletes a city by id
        this.cityList.remove(city);

    }

    public List<City> returnAllCities(String id){
        //returns all cities in the db
        return this.cityList.stream()
                .filter(city -> city.getId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
    }

}
