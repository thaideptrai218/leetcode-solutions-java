package arrays;

import java.util.Scanner;

public class BMILOL {
    static Scanner sc = new Scanner(System.in);

    enum Operator {
        ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"), POWER("^"), EQUAL("=");
        final String symbol;
        Operator(String s) {
            this.symbol = s;
        }
    }

    enum BMI { UNDERWEIGHT, STANDARD, OVERWEIGHT, FAT, VERYFAT }
    
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("========= Calculator Program =========");
            System.out.println("1. Normal Calculator");
            System.out.println("2. BMI Calculator");
            System.out.println("3. Exit");
            System.out.print("Please choice one option: ");
            choice = readInt();
            switch (choice) {
                case 1: normalCalculator(); break;
                case 2: bmiCalculator(); break;
                case 3: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid option!");
            }
        } while (choice != 3);
    }

    private static void bmiCalculator() {
        System.out.println("----- Normal Calculator -----");
        double memory = inputNumber("Enter number: ", "Number is digit");
    }

    private static void normalCalculator() {
        System.out.println("----- Normal Calculator -----");
        double memory = inputNumber("Enter number: ", "Number is digit");
        while (true) {
            Operator op = inputOperator();
            if (op == Operator.EQUAL) { System.out.println("Result :" + memory); break; }
            double b = inputNumber("Enter number: ", "Number is digit");
            try {
                memory = calculate(memory, op, b);
                System.out.println("Memory :" + memory);
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero");
            }
        }
    }


    static double inputNumber(String prompt, String err) {
        while (true) {
            System.out.print(prompt);
            Double v = checkin(sc.nextLine());
            if (v != null) return v;
            System.out.println(err);
        }
    }
    static Operator inputOperator() {
        while (true) {
            System.out.print("Enter Operator: ");
            Operator op = checkOperator(sc.nextLine());
            if (op != null) return op;
            System.out.println("Please input (+, -, *, /, ^)");
        }
    }
    static int readInt() {
        try { return Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { return -1; }
    }

    public static Operator checkOperator(String op) {
        for (Operator o : Operator.values())
            if (o.symbol.equals(op.trim())) return o;
        return null;
    }
    public static Double checkin(String s) {
        try { return Double.parseDouble(s.trim()); } catch (Exception e) { return null; }
    }
    public static double calculate(double a, Operator op, double b) {
        switch (op) {
            case ADD: return a + b;
            case SUBTRACT: return a - b;
            case MULTIPLY: return a * b;
            case DIVIDE:
                if (b == 0) throw new ArithmeticException("div0");
                return a / b;
            case POWER: return Math.pow(a, b);
            default: return b;
        }
    }
}
