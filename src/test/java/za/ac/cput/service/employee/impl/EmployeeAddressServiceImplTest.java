package za.ac.cput.service.employee.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.employee.EmployeeAddress;
import za.ac.cput.domain.location.City;
import za.ac.cput.domain.location.Country;
import za.ac.cput.domain.lookup.Address;
import za.ac.cput.factory.employee.EmployeeAddressFactory;
import za.ac.cput.factory.location.CityFactory;
import za.ac.cput.factory.location.CountryFactory;
import za.ac.cput.factory.lookup.AddressFactory;
import za.ac.cput.repository.location.CityRepository;
import za.ac.cput.service.location.impl.CityServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeAddressServiceImplTest {
    private EmployeeAddress employeeAddress;

    private Address address;
    private City city;
    private Country country;

    @Autowired
    private EmployeeAddressServiceImpl employeeAddressService;

    @BeforeEach
    public void startUp(){
        this.employeeAddress = EmployeeAddressFactory.build("staff-id",address);
        this.address = AddressFactory.build("unit-number", "complex-name", "street-number", "street-name", 1234, city);
        this.city = CityFactory.buildCity("city-id","city-name",country);
        this.country = CountryFactory.buildCountry("country-id","country-name");
    }

    @Test
    @Order(1)
    void save() {
        EmployeeAddress saveEmployeeAddress = this.employeeAddressService.save(employeeAddress);
        assertEquals(this.employeeAddress, saveEmployeeAddress);
    }

    @Test
    @Order(2)
    void read() {
        Optional<EmployeeAddress> readEmployeeAddress = this.employeeAddressService.read(employeeAddress.getStaffId());
        assertAll(
                () -> assertTrue(readEmployeeAddress.isPresent()),
                () -> assertEquals(employeeAddress, readEmployeeAddress.get())
        );
    }

    @Test
    @Order(4)
    void delete() {
        this.employeeAddressService.delete(this.employeeAddress);
        findAll();
    }


    void findAll(){
        List<EmployeeAddress> employeeAddressList = this.employeeAddressService.findAll();
        assertEquals(0, employeeAddressList.size());
    }
    @Test
    @Order(3)
    void deleteById() {
        this.employeeAddressService.deleteById(employeeAddress.getStaffId());
        findAll();
    }


}