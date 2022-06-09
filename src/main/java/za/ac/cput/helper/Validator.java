package za.ac.cput.helper;

import org.apache.commons.lang3.StringUtils;

public class Validator {
    public static boolean isEmptyOrNull(String s) {
        return StringUtils.isEmpty(s);
    }

    public static void checkStringParam(String paramName, String paramValue) {
        if(isEmptyOrNull(paramValue))
            throw new IllegalArgumentException(
                    String.format("Invalid value for params: %s", paramName)
            );
    }
}
