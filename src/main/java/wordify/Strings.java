package wordify;

public class Strings {
    public static final String EMPTY = "";

    public static String capitalise(String value) {
        if (isEmpty(value)) return value;
        return String.valueOf(Character.toTitleCase(value.charAt(0))) + value.substring(1);
    }

    public static boolean isEmpty(String value) {
        return value == null || value.equals(EMPTY);
    }
}
