package ch.g_7.util.helper;

import java.util.regex.Pattern;

public class Validation {


    public static boolean isInteger(String value){
        if (value == null) {
            return false;
        }
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isFloat(String value){
        if (value == null) {
            return false;
        }
        try {
            Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the string starts with [a-zA-Z_] and contains [0-9a-zA-Z_]
     * @param string
     * @return
     */
    public static boolean isValidString(String string){
        return Pattern.matches("[a-zA-Z_]+[0-9a-zA-Z_]*", string);
    }

    public static boolean isInBounds(int i, int min, int max){
        return i >= min && i <= max;
    }
}
