package za.ac.cput.factory.employee;

/*
 EmployeeAddressFactory.java domain
 Student Name: Mike Somelezo Tyolani
 Student Number: 220187568
*/

import za.ac.cput.domain.employee.EmployeeAddress;
import za.ac.cput.domain.lookup.Address;
import za.ac.cput.helper.Validator;

public class EmployeeAddressFactory {
    public static EmployeeAddress build(String staffId, Address address) {
        Validator.checkStringParam("staffId", staffId);
        Validator.isNull("address", address);

        return new EmployeeAddress.Builder()
                .staffId(staffId)
                .Address(address)
                .build();
    }
}
