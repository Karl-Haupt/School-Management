package za.ac.cput.controller.location;
/*
 This is the city controller implementation test cases
 Name & Surname: Charles Lemmert
 Student No: 220498385
*/
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityControllerTest {
    @LocalServerPort
    private int cityPort;

    @Autowired
    private CityController cityController;

    @Autowired
    private CountryController countryController;

    @Autowired
    private TestRestTemplate cityRestTemp;

    private City city;
    private  String cityBaseUrl;

    @BeforeEach
    public void startUp(){
        assertNotNull(cityController);
        Country countryObj = CountryFactory.buildCountry("1-RSA","South Africa");
        this.city = CityFactory.buildCity("1-CPT"," Cape Town",countryObj);
        this.cityBaseUrl = "http://localhost:" + this.cityPort + "/api/v1/school-management/city/";

    }
    @Test
    @Order(1)
    void save() {
        AddCountryToDB();
        String saveUrl = cityBaseUrl + "save";
        ResponseEntity<City> saveResponse = this.cityRestTemp.
                postForEntity(saveUrl,this.city, City.class);
        System.out.println(saveResponse);
        assertAll(
                () -> assertEquals(HttpStatus.OK, saveResponse.getStatusCode()),
                () -> assertNotEquals(null,saveResponse)
        );
    }

    @Test
    @Order(2)
    void read() {
        String cityUrl = cityBaseUrl + "read/" + this.city.getId();
        ResponseEntity<City> readResponse = this.cityRestTemp.getForEntity(cityUrl,City.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, readResponse.getStatusCode()),
                () -> assertNotEquals(null,readResponse)
        );
    }

    @Test
    @Order(3)
    void findCityByCountryID() {
        AddCountryToDB();
        String byCountryUrl = cityBaseUrl + "read?countryID=" + city.getCountry().getCountryID();
        ResponseEntity<String[]> cityCountryResponse = this.cityRestTemp.getForEntity(byCountryUrl, String[].class);
        for(var n: cityCountryResponse.getBody()){
            System.out.println(n);
        }
        assertAll(
                () -> assertEquals(HttpStatus.OK, cityCountryResponse.getStatusCode()),
                () -> assertNotNull(cityCountryResponse.getBody())
        );
    }
    void AddCountryToDB(){
        var country = CountryFactory.buildCountry("1-RSA","South Africa");
        countryController.save(country);
    }
    @Test
    @Order(4)
    void deleteById() {
        String deleteByIdUrl = cityBaseUrl + "delete/" + this.city.getId();
        this.cityRestTemp.delete(deleteByIdUrl);
    }

    @Test
    @Order(6)
    void delete() {
        String deleteUrl = cityBaseUrl + "delete";
        this.cityRestTemp.delete(deleteUrl);
    }

    @Test
    @Order(5)
    void findAllCities() {
        String findAllUrl = cityBaseUrl +"all";
        ResponseEntity<City[]> findAllResponse = this.cityRestTemp.
                getForEntity(findAllUrl,City[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, findAllResponse.getStatusCode()),
                () -> assertNotNull(findAllResponse.getBody()),
                () -> assertTrue(findAllResponse.getBody().length == 0)

        );
    }
}