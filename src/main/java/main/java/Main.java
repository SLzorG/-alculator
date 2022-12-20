package main.java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите выражение");
            String input = scanner.nextLine();
            System.out.println(calc(input));
        }
    }

    public static String calc(String input) throws Exception {
        int num1, num2;
        String oper, result;
        boolean isRoman;
        String[] operation = input.split("[+\\-*/]");
        if (operation.length > 2)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        oper = detecterOperation(input);
        if (Roman.isRoman(operation[0]) && Roman.isRoman(operation[1])) {
            num1 = Roman.convertToArabic(operation[0]);
            num2 = Roman.convertToArabic(operation[1]);
            isRoman = true;
        } else if (!Roman.isRoman(operation[0]) && !Roman.isRoman(operation[1])) {
            num1 = Integer.parseInt(operation[0]);
            num2 = Integer.parseInt(operation[1]);
            isRoman = false;
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа далжны быть от 0 до 10");
        }
        int arabic = calcularion(num1, num2, oper);
        if (isRoman) {
            if (arabic <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            result = Roman.convertToRoman(arabic);
        } else {
            result = String.valueOf(arabic);
        }
        return result;
    }


    static String detecterOperation(String input) throws Exception {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else throw new Exception("Строка не является математической операцией");

    }

    static int calcularion(int num1, int num2, String oper) {

        int z;
        switch (oper) {
            case "+":
                z = num1 + num2;
                break;
            case "-":
                z = num1 - num2;
                break;
            case "/":
                z = num1 / num2;
                break;
            default:
                z = num1 * num2;
                break;
        }
        return z;
    }

    static class Roman {
        static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
                "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX",
                "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
                "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX",
                "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
                "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX",
                "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX",
                "LXX", "LXXI", "LXXII", "LXXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXXIX",
                "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
                "XC", "XCI", "XII", "XCIII", "XCIV", "XCV", "XCVI", "XVII", "XCVIII", "ХХIХ", "C"};


        static boolean isRoman(String val) {
            for (int i = 0; i < romanArray.length; i++) {
                if (val.equals(romanArray[i])) {
                    return true;
                }
            }
            return false;
        }

        static int convertToArabic(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }
            }
            return -1;
        }

        static String convertToRoman(int arabic) {
            return romanArray[arabic];
        }
    }
}

