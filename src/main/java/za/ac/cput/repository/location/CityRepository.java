package za.ac.cput.repository.location;

import za.ac.cput.domain.location.City;

import java.util.*;
import java.util.stream.Collectors;

/*
 This is the city Repository
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
public class CityRepository {
    private final List<City> cityList;
    private static CityRepository cityRepo;

    private CityRepository(){
        this.cityList = new ArrayList<>();
    }
    public static CityRepository cityRepository(){
        if(cityRepo == null) cityRepo = new CityRepository();
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
