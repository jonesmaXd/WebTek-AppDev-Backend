package no.ntnu.WebTek.AppDevbackend.validators;

/**
 * Class containing methods to validate strings
 */
public class StringValidators {

    /**
     * Checks if a string is valid or not.
     * The string is invalid if it is null or blank.
     *
     * @param string the string to be validated.
     * @return boolean, true if the parameter is null or is blank.
     */
    public static boolean isStringInvalid(String string) {
        return string == null || string.isBlank();
    }
}
