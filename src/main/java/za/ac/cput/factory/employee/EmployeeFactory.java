package za.ac.cput.factory.employee;

import za.ac.cput.domain.employee.Employee;
import za.ac.cput.domain.lookup.Name;
import za.ac.cput.helper.Validator;

/*
 EmployeeFactory.java -> Allows for a singple point in the application to create Employee entities
 Student Name: Karl Haupt
 Student Number: 220236585
*/

public class EmployeeFactory {

    public static Employee buildEmployee(String staffID, String email, Name name) {
        Validator.checkStringParam("staffID", staffID);
        Validator.isValidEmail("email", email);
        Validator.isNull("name", name);

        return new Employee.Builder()
                .setStaffID(staffID)
                .setEmail(email)
                .setName(name)
                .build();
    }
}
