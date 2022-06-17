package za.ac.cput.api.employee;

import org.junit.jupiter.api.Test;
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
import za.ac.cput.service.employee.EmployeeAddressService;
import za.ac.cput.service.employee.EmployeeService;
import za.ac.cput.service.location.ICityService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeAddressAPITest {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeAddressService employeeAddressService;
    @Autowired
    private ICityService iCityService;

    private EmployeeAddress employeeAddress;
    private Country country;
    private City city;
    private Address address;

    @Autowired
    private EmployeeAddressAPI api;


    public void setUp() {
        this.employeeAddress = EmployeeAddressFactory
                .build("", address);
        this.address = AddressFactory.build("", "", "", "", 1234, city);

        this.city = CityFactory.buildCity("", "", country);

        this.country = CountryFactory.buildCountry("", "");
    }

    @Test
    void save() {
        EmployeeAddress saved = this.api.save(this.employeeAddress);
    }
}