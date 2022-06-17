package za.ac.cput.controller.location;

/* CountryController.java
   Controller for Country
   Author: Joshua Daniel Jonkers(215162668)
   Date: 15/06/2022
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.location.Country;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.service.location.impl.CountryServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/school-management/country/")
@Slf4j
public class CountryController {
    private final CountryServiceImpl countryService;

    @Autowired
    public CountryController(CountryServiceImpl countryService) { this.countryService = countryService; }

    @PostMapping("save")
    public ResponseEntity<Country> save(@Valid @RequestBody Country country) {
        log.info("Request to save: {}", country);
        Country newCountry = CountryFactory.buildCountry(country.getCountryID(), country.getCountryName());
        return ResponseEntity.ok(countryService.save(newCountry));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Country> read(@PathVariable String id) {
        Country readCountry = this.countryService.read(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
        return ResponseEntity.ok(readCountry);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(Country country) {
        log.info("Request to delete: {}", country);
        this.countryService.delete(country);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        log.info("Request to delete by id: {}", id);
        this.countryService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<Country>> findAll() {
        List<Country> countryList = this.countryService.findAll();
        return ResponseEntity.ok(countryList);
    }
}
