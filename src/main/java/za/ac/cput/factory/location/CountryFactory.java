package za.ac.cput.factory.location;

/* CountryFactory.java
   Factory for the Country
   Author: Joshua Daniel Jonkers(215162668)
   Date: 11/06/2022
 */

import za.ac.cput.domain.location.Country;
import za.ac.cput.helper.Validator;

public class CountryFactory {
    public static Country buildCountry(String countryID, String countryName) {
        Validator.checkStringParam("countryID", countryID);
        Validator.checkStringParam("countryName", countryName);

        return new Country.Builder()
                .setCountryID(countryID)
                .setCountryName(countryName)
                .build();
    }
}
