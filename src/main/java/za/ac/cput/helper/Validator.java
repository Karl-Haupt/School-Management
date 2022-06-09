package za.ac.cput.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

public class Validator {
    public static boolean isEmptyOrNull(String s) {
        return StringUtils.isEmpty(s);
    }

    public static void isNull(String paramName, Object o) {
        if(o == null) throw new IllegalArgumentException(
                String.format("Invalid value for params: %s", paramName)
        );
    }
    public static void checkStringParam(String paramName, String paramValue) {
        if(isEmptyOrNull(paramValue))
            throw new IllegalArgumentException(
                    String.format("Invalid value for params: %s", paramName)
            );
    }

    public static boolean isValidEmail(String emailAddress){
        EmailValidator validateEmail = EmailValidator.getInstance();
        return validateEmail.isValid(emailAddress);
    }
}
