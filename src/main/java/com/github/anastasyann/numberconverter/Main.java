package com.github.anastasyann.numberconverter;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Please, input number:");

        String input = scanner.nextLine();
        Long number = null;
        String numberStr = "";

        boolean isInputNumeric = false;

        if (isValidInput(input)) {
            System.out.println("Input: " + input);
            if (isNumber(input)) {
                number = Long.parseLong(input);
                isInputNumeric = true;
            } else {
                numberStr = input;
            }
        } else {
            System.out.println("Invalid input!");
            return;
        }

        if (isInputNumeric) {
            System.out.println(String.format("Output: %s", NumberToWordConverter.getWordsFromNumber(number)));
        } else {
            number = WordToNumberConverter.getNumberFromWords(numberStr);
            if (number == Long.MAX_VALUE) {
                System.out.println("Invalid input!");
            } else {
                System.out.println(String.format("Output: %s", number));
            }
        }

        //new UserInterface();
    }

    private static boolean isNumber(String usersInput) {
        //  -9,223,372,036,854,775,808 ...  9,223,372,036,854,775,807
        if (usersInput.length() <= 19) {
            try {
                Long.parseLong(usersInput);
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return false;
    }

    private static boolean isValidInput(String usersInput) {
        if (isNumber(usersInput)) {
            long input = Long.parseLong(usersInput);
            return (Math.abs(input) < Math.pow(10, 12));
        }
        return !StringUtils.isBlank(usersInput);
    }
}
