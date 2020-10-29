package by.minsk.perform.util;

/**
 * @author Alena_Papruha
 * @version 1.1
 * @since 28 Oct, 2020
 */

public class Util {

    private Util() {
    }

    public static String[] getTrackArray(String string) {
        String newString = string.substring(1, string.length() - 1).trim();
        return newString.split(", ");
    }

}
