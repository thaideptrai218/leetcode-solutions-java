package sorting;

import java.util.Arrays;
import java.util.StringJoiner;

public class ConsoleOutput {
    private ConsoleOutput() {
    }

    public static void banner(String title, char fill, int padding) {
        String bar = String.valueOf(fill).repeat(Math.max(0, padding));
        System.out.println(bar + title + bar);
    }

    public static void banner(String left, char title, String right) {
        System.out.println(left + title + right);
    }

    public static void line(String msg) {
        System.out.println(msg);
    }

    public static void print(String msg) {
        System.out.print(msg);
    }

    public static void blank() {
        System.out.println();
    }

    public static void log(Object... args) {
        if (args == null || args.length == 0) {
            System.out.println();
            return;
        }

        StringJoiner stringJoiner = new StringJoiner(" ");
        for (Object obj : args) {
            if (obj instanceof Object[]) {
                stringJoiner.add(Arrays.toString((Object[]) obj));
            } else {
                stringJoiner.add(String.valueOf(obj));
            }
        }
        System.out.println(stringJoiner.toString());
    }

    public static void menu(String[] options, String trailingPrompt) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        if (trailingPrompt != null && !trailingPrompt.isEmpty()) {
            System.out.println(trailingPrompt);
        }
    }
}
