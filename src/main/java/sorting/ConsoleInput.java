package sorting;

import java.util.Scanner;

public class ConsoleInput {
    public ConsoleInput() {
    }

    private final Scanner scanner = new Scanner(System.in);

    public int readInt(String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            Integer n = Validator.tryParseInt(scanner.nextLine());
            if (n != null) return n;
            System.out.println(errorMsg);
        }
    }

    public int readIntMin(String prompt, int min, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            Integer n = Validator.tryParseInt(scanner.nextLine());
            if (n != null && n >= min) return n;
            System.out.println(errorMsg);
        }
    }

    public int readIntInRange(String prompt, int min, int max, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            Integer n = Validator.tryParseInt(scanner.nextLine());
            if (n != null && Validator.inRange(n, min, max)) return n;
            System.out.println(errorMsg);
        }
    }

    public double readDouble(String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            Double d = Validator.tryParseDouble(scanner.nextLine());
            if (d != null) return d;
            System.out.println(errorMsg);
        }
    }

    public double readDoubleMin(String prompt, double min, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            Double d = Validator.tryParseDouble(scanner.nextLine());
            if (d != null && d >= min) return d;
            System.out.println(errorMsg);
        }
    }

    public double readDoubleInRange(String prompt, double min, double max, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            Double d = Validator.tryParseDouble(scanner.nextLine());
            if (d != null && Validator.inRange(d, min, max)) return d;
            System.out.println(errorMsg);
        }
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public String readNonBlank(String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            if (Validator.isNotBlank(s)) return s.trim();
            System.out.println(errorMsg);
        }
    }

    public String readPattern(String prompt, String regex, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            if (s.matches(regex)) return s;
            System.out.println(errorMsg);
        }
    }

    public void close() {
        scanner.close();
    }
}
