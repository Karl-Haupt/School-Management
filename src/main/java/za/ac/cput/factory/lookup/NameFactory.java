/*
Author: Trevor Ngcobo (220477019)
Subject: Applications Development Practice 3
Group: 18
Assessment: June Group Assignment 2022
*/

package za.ac.cput.factory.lookup;

import za.ac.cput.domain.lookup.Name;
import za.ac.cput.helper.Validator;

public class NameFactory
{
    public static Name buildName(String fName, String mName, String lName)
    {
        Validator.checkStringParam("First Name", fName);
        Validator.isNull("Middle Name", mName);
        Validator.checkStringParam("Last Name", lName);

        return new Name.Builder().setFirstName(fName).setMiddleName(mName).setLastName(lName).build();
    }

}
