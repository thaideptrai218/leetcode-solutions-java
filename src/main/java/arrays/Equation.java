package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Equation {
    static Scanner sc = new Scanner(System.in);

    public static void main() {
        int choice;
        do {
            System.out.println("========= Equation Program =========");
            System.out.println("1. Calculate Superlative Equation");
            System.out.println("2. Calculate Quadratic Equation");
            System.out.println("3. Exit");
            System.out.print("Please choice one option: ");
            choice = readInt();
            switch (choice) {
                case 1:
                    doLinear();
                    break;
                case 2:
                    doQuadratic();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");

            }
        } while (choice != 3);
    }

    static void doLinear() {
        System.out.println("----- Calculate Equation -----");
        float a = inputNumber("Enter A: ");
        float b = inputNumber("Enter B: ");
        List<Float> sol = calculateEquation(a, b);
        List<Float> nums = new ArrayList<>(Arrays.asList(a, b));
        if (sol == null) {
            System.out.println("Solution: No solution");
        } else if (sol.isEmpty()) {
            System.out.println("Solution: Infinitely many solutions");
        } else {
            System.out.printf("Solution: x = %.3f%n", sol.get(0));
            nums.addAll(sol);
        }
        showClassification(nums);
    }

    static void doQuadratic() {
        System.out.println("----- Calculate Quadratic Equation -----");
        float a = inputNumber("Enter A : ");
        float b = inputNumber("Enter B: ");
        float c = inputNumber("Enter C: ");
        List<Float> sol = calculateQuadraticEquation(a, b, c);
        List<Float> nums = new ArrayList<>(Arrays.asList(a, b, c));
        if (sol == null) {
            System.out.println("Solution: No solution");
        } else if (sol.size() == 1)  {
            System.out.printf("Solution: x1 = %.3f and x2 = %.3f%n", sol.get(0), sol.get(0));
            nums.add(sol.get(0)); nums.add(sol.get(0));
        } else {
            System.out.printf("Solution: x1 = %.3f and x2 = %.3f%n", sol.get(0), sol.get(1));
            nums.addAll(sol);
        }
        showClassification(nums);
    }

    public static List<Float> calculateEquation(float a, float b) {
        if (a == 0 && b == 0) return new ArrayList<>();   // vô số nghiệm
        if (a == 0) return null;                          // vô nghiệm
        List<Float> s = new ArrayList<>();
        s.add(-b / a);
        return s;
    }

    static List<Float> calculateQuadraticEquation(float a, float b, float c) {
        if (a == 0) return calculateEquation(b, c);
        float delta = a * a - 4 * b * c;
        if (delta < 0) return null;
        List<Float> sol = new ArrayList<>();
        if (delta == 0) {
            sol.add(-b / (2 * a));
        } else {
            float sq = (float) Math.sqrt(delta);
            sol.add((-b + sq) / (2 * a));
            sol.add((-b - sq) / (2 * a));
        }
        return sol;
    }


    public static void showClassification(List<Float> nums) {
        System.out.println("Number is Odd :" + join(filter(nums, "odd")));
        System.out.println("Number is Even :" + join(filter(nums, "even")));
        System.out.println("Number is Perfect Square :" + join(filter(nums, "square")));
    }

    public static List<Float> filter(List<Float> nums, String type) {
        List<Float> res = new ArrayList<>();
        for (float f : nums) {
            if (type.equals("odd") && isOdd(f)) res.add(f);
            if (type.equals("even") && !isOdd(f)) res.add(f);
            if (type.equals("square") && isPerfectSquare(f)) res.add(f);
        }
        return res;
    }

    public static String join(List<Float> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public static boolean isOdd(float f) {
        return f % 2 == 1;
    }

    public static boolean isPerfectSquare(float n) {
        if (n < 0) return false;
        double r = Math.sqrt(n);
        return r == Math.floor(r);
    }

    public static Float checkin(String s) {
        try {
            return Float.parseFloat(s.trim());
        } catch (Exception e) {
            return null;
        }
    }

    static float inputNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            Float v = checkin(sc.nextLine());
            if (v != null) return v;
            System.out.println("Please input number");
        }
    }

    static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}

