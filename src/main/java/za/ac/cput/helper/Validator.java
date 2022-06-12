package za.ac.cput.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class Validator {
    public static boolean isEmptyOrNull(String s) {
        return StringUtils.isEmpty(s);
    }

    public static void isNull(String paramName, Object o)
    {
        if(o == null) throw new IllegalArgumentException(
                String.format("Invalid value for params: %s", paramName)
        );
    }
    public static void checkStringParam(String paramName, String paramValue)
    {
        if(isEmptyOrNull(paramValue))
            throw new IllegalArgumentException(
                    String.format("Invalid value for params: %s", paramName)
            );
    }


    public static boolean isValidEmail(String paramName, String emailAddress){
        EmailValidator validateEmail = EmailValidator.getInstance();
        if (!validateEmail.isValid(emailAddress))
            throw new IllegalArgumentException(
                    String.format("Invalid value for params: %s", paramName)
            );
        return validateEmail.isValid(emailAddress);
    }

    public static boolean isValidPostalCode(String paramName, int postalCode) {
        int postalCodeLength = String.valueOf(postalCode).length();
        if(!isValidRange(postalCode, 1000, 9999) && postalCodeLength != 4)
            throw new IllegalArgumentException(
                    String.format("Invalid value for params: %s", paramName)
            );
        return true;
    }

    private static boolean isValidRange(int value, int begin, int end) {
        return value > begin && value < end;
    }
}
