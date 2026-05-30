package sorting;

public class Validator {
    private Validator(){}

    public static Integer tryParseInt(String s) {
        if (s == null) return null;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double tryParseDouble(String s) {
        if (s == null) return null;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean isPositive(int n) {return n > 0;}
    public static boolean isPositive (double n) {return n > 0;}

    public static boolean inRange(int n, int min, int max) {return n >= min && n <= max;}
    public static boolean inRange(double n, double min, double max) {return n >= min && n <= max;}

    public static boolean isNotBlank (String s) {
        return s != null && !s.trim().isEmpty();
    }
}
