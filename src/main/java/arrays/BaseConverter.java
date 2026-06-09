package arrays;

import java.util.Scanner;

public class BaseConverter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== BASE CONVERTER =====");
            System.out.println("1. Binary");
            System.out.println("2. Decimal");
            System.out.println("3. Hexadecimal");

            System.out.print("Choose input base: ");
            int inputChoice = sc.nextInt();

            System.out.print("Choose output base: ");
            int outputChoice = sc.nextInt();

            System.out.print("Enter value: ");
            String value = sc.next();

            int inputBase = getBase(inputChoice);
            int outputBase = getBase(outputChoice);

            long decimalValue = toDecimalNaive(value, inputBase);
            String result = fromDecimal(decimalValue, outputBase);

            System.out.println("Result: " + result);
        }
    }

    private static int getBase(int choice) {
        switch (choice) {
            case 1:
                return 2;
            case 2:
                return 10;
            case 3:
                return 16;
            default:
                throw new IllegalArgumentException("Invalid choice");
        }
    }

    /**
     * Naive:
     * d_n*b^n + d_(n-1)*b^(n-1) + ... + d_0
     */
    private static long toDecimalNaive(String value, int base) {
        long result = 0;
        value = value.toUpperCase();

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            int digit;

            if (c >= '0' && c <= '9') {
                digit = c - '0';
            } else if (c >= 'A' && c <= 'F') {
                digit = c - 'A' + 10;
            } else {
                digit = 0;
            }

            int exponent = value.length() - i - 1;
            result += digit * power(base, exponent);
        }
        return result;
    }

    private static long power(int base, int exponent) {
        long result = 1;

        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        return result;
    }

    private static String fromDecimal(long value, int base) {
        if (value == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        while (value > 0) {
            int digit = (int) value % base;

            if (digit < 10) {
                sb.append(digit);
            } else if (digit < 16) {
                sb.append((char) 'A' + digit - 10);
            } else {
                sb.append("|");
            }

            value /= base;
        }
        return sb.reverse().toString();
    }
}