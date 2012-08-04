package spreadsheet.utils;

public class Util {

    private Util() {}

    public static boolean isNumber(String value) {
        String str = value.trim();
        return str.matches("\\A\\d+\\z") || str.matches("\\A\\d+\\.\\d*\\z");
    }

}
