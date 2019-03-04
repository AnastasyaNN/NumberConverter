package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<Long, String> numberToWord = new HashMap<>();
    private static Map<String, Long> wordToNumber = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMenu();

        String input = scanner.nextLine();
        Long number = null;
        String numberStr = "";

        if (isValidInput(input)) {
            System.out.println("Input: " + input);
            if (isNumber(input)) {
                number = Long.parseLong(input);
            } else {
                numberStr = input;
            }
        } else {
            System.out.println("Invalid input!");
            return;
        }

        if (number != null) {
            fillNumberToWordMap();
            System.out.println(String.format("Output: %s", getWordsFromNumber(number)));
        } else if (!numberStr.equals("")) {
            fillWordToNumberMap();
            number = getNumberFromWords(numberStr);
            if (number == Long.MAX_VALUE) {
                System.out.println("Invalid input!");
            } else {
                System.out.println(String.format("Output: %s", number));
            }
        }

    }

    private static void fillNumberToWordMap() {
        numberToWord.put(0L, "zero");
        numberToWord.put(1L, "one");
        numberToWord.put(2L, "two");
        numberToWord.put(3L, "three");
        numberToWord.put(4L, "four");
        numberToWord.put(5L, "five");
        numberToWord.put(6L, "six");
        numberToWord.put(7L, "seven");
        numberToWord.put(8L, "eight");
        numberToWord.put(9L, "nine");
        numberToWord.put(10L, "ten");
        numberToWord.put(11L, "eleven");
        numberToWord.put(12L, "twelve");
        numberToWord.put(13L, "thirteen");
        numberToWord.put(14L, "fourteen");
        numberToWord.put(15L, "fifteen");
        numberToWord.put(16L, "sixteen");
        numberToWord.put(17L, "seventeen");
        numberToWord.put(18L, "eighteen");
        numberToWord.put(19L, "nineteen");
        numberToWord.put(20L, "twenty");
        numberToWord.put(30L, "thirty");
        numberToWord.put(40L, "forty");
        numberToWord.put(50L, "fifty");
        numberToWord.put(60L, "sixty");
        numberToWord.put(70L, "seventy");
        numberToWord.put(80L, "eighty");
        numberToWord.put(90L, "ninety");
        numberToWord.put(100L, "hundred");
        numberToWord.put(1000L, "thousand");
        numberToWord.put(1_000_000L, "million");
        numberToWord.put(1_000_000_000L, "billion");
    }

    private static void fillWordToNumberMap() {
        wordToNumber.put("zero", 0L);
        wordToNumber.put("one", 1L);
        wordToNumber.put("two", 2L);
        wordToNumber.put("three", 3L);
        wordToNumber.put("four", 4L);
        wordToNumber.put("five", 5L);
        wordToNumber.put("six", 6L);
        wordToNumber.put("seven", 7L);
        wordToNumber.put("eight", 8L);
        wordToNumber.put("nine", 9L);
        wordToNumber.put("ten", 10L);
        wordToNumber.put("eleven", 11L);
        wordToNumber.put("twelve", 12L);
        wordToNumber.put("thirteen", 13L);
        wordToNumber.put("fourteen", 14L);
        wordToNumber.put("fifteen", 15L);
        wordToNumber.put("sixteen", 16L);
        wordToNumber.put("seventeen", 17L);
        wordToNumber.put("eighteen", 18L);
        wordToNumber.put("nineteen", 19L);
        wordToNumber.put("twenty", 20L);
        wordToNumber.put("thirty", 30L);
        wordToNumber.put("forty", 40L);
        wordToNumber.put("fifty", 50L);
        wordToNumber.put("sixty", 60L);
        wordToNumber.put("seventy", 70L);
        wordToNumber.put("eighty", 80L);
        wordToNumber.put("ninety", 90L);
        wordToNumber.put("hundred", 100L);
        wordToNumber.put("thousand", 1000L);
        wordToNumber.put("million", 1_000_000L);
        wordToNumber.put("billion", 1_000_000_000L);
    }

    private static Long getNumberFromWords(String string) {
        string = skipEscapes(string);
        boolean isNegative = false;

        if (string.equals("zero")) {
            return wordToNumber.get("zero");
        }

        if (string.contains("minus")) {
            isNegative = true;
            string = string.substring("minus ".length());
        }

        Long number = Long.parseLong("0");

        if (string.contains("billion")) {
            Long currentNumber = getNumberLessThan1000FromWords(string.split(" billion ")[0]);
            if (currentNumber == Long.MAX_VALUE) {
                return currentNumber;
            }
            number = currentNumber * Math.round(Math.pow(10, 9));

            string = string.substring(string.indexOf(" billion ") + " billion ".length());
        }
        if (string.contains("million")) {
            Long currentNumber = getNumberLessThan1000FromWords(string.split(" million ")[0]);
            if (currentNumber == Long.MAX_VALUE) {
                return currentNumber;
            }
            number += currentNumber * Math.round(Math.pow(10, 6));

            string = string.substring(string.indexOf(" million ") + " million ".length());
        }
        if (string.contains("thousand")) {
            Long currentNumber = getNumberLessThan1000FromWords(string.split(" thousand ")[0]);
            if (currentNumber == Long.MAX_VALUE) {
                return currentNumber;
            }
            number += currentNumber * Math.round(Math.pow(10, 3));

            string = string.substring(string.indexOf(" thousand ") + " thousand ".length());
        }
        Long currentNumber = getNumberLessThan1000FromWords(string);
        if (currentNumber == Long.MAX_VALUE) {
            return currentNumber;
        }
        number += currentNumber;

        return isNegative ? -number : number;
    }

    private static Long getNumberLessThan1000FromWords(String string) {
        Long number = Long.MAX_VALUE;
        long hundreds;

        // e.x. one hundred and twenty-five
        if (string.contains(" and ")) {
            Long getHundreds = getHundredsFromWords(string.substring(0, string.indexOf(" hundred")));
            if (getHundreds != null) {
                hundreds = getHundreds;
            } else {
                return number;
            }
            // e.x. one hundred and eleven
            if (!string.split(" and ")[1].contains("-")) {
                Long getUnitsFromMap = wordToNumber.get(string.split(" and ")[1]);
                return (getUnitsFromMap != null) ? hundreds + getUnitsFromMap : number;
            } else {
                Long getTensAndUnits = getTensAndUnitsFromWords(string.split(" and ")[1]);
                return (getTensAndUnits != null) ? hundreds + getTensAndUnits : number;
            }
        } else if (string.contains(" hundred")) {
            Long getHundreds = getHundredsFromWords(string.substring(0, string.indexOf(" hundred")));
            return (getHundreds != null) ? getHundreds : number;
        } else if (string.contains("-")) {
            Long getTensAndUnits = getTensAndUnitsFromWords(string);
            return (getTensAndUnits != null) ? getTensAndUnits : number;
        } else if (!string.contains("-")) {
            Long getUnitsFromMap = wordToNumber.get(string);
            return (getUnitsFromMap != null) ? getUnitsFromMap : number;
        }

        return number;
    }

    private static Long getHundredsFromWords(String str) {
        Long getHundredsFromMap = wordToNumber.get(str);
        if (getHundredsFromMap != null) {
            return getHundredsFromMap * 100;
        } else {
            return null;
        }
    }

    private static Long getTensAndUnitsFromWords(String str) {
        Long getTensFromMap = wordToNumber.get(str.split("-")[0]);
        Long getUnitsFromMap = wordToNumber.get(str.split("-")[1]);
        if (getTensFromMap != null && getUnitsFromMap != null) {
            return getTensFromMap + getUnitsFromMap;
        } else {
            return null;
        }
    }

    private static String getWordsFromNumber(Long number) {

        if (number == 0) {
            return numberToWord.get(0L);
        }

        StringBuilder stringBuilder = new StringBuilder();

        if (number < 0) {
            stringBuilder.append("minus ");
            number = Math.abs(number);
        }

        long billions = number / Math.round(Math.pow(10, 9));
        //System.out.println(billions);
        if (billions != 0) {
            stringBuilder.append(getWordsFromNumberLessThan1000(billions));
            stringBuilder.append(numberToWord.get(1_000_000_000L));
            stringBuilder.append(" ");
        }

        long millions = (number % Math.round(Math.pow(10, 9))) / Math.round(Math.pow(10, 6));
        //System.out.println(millions);
        if (millions != 0) {
            stringBuilder.append(getWordsFromNumberLessThan1000(millions));
            stringBuilder.append(numberToWord.get(1_000_000L));
            stringBuilder.append(" ");
        }

        long thousands = (number % Math.round(Math.pow(10, 6))) / Math.round(Math.pow(10, 3));
        //System.out.println(thousands);
        if (thousands != 0) {
            stringBuilder.append(getWordsFromNumberLessThan1000(thousands));
            stringBuilder.append(numberToWord.get(1000L));
            stringBuilder.append(" ");
        }

        long hundreds = number % Math.round(Math.pow(10, 3));
        //System.out.println(hundreds);
        if (hundreds != 0) {
            stringBuilder.append(getWordsFromNumberLessThan1000(hundreds));
        }

        return stringBuilder.toString();
    }

    private static String getWordsFromNumberLessThan1000(Long number) {
        StringBuilder stringBuilder = new StringBuilder();

        long hundreds = number / 100;
        long tens = number % 100;
        long units;

        if (hundreds != 0) {
            stringBuilder.append(numberToWord.get(hundreds));
            stringBuilder.append(" ");
            stringBuilder.append(numberToWord.get(100L));
            stringBuilder.append(" ");
            if (tens != 0) {
                stringBuilder.append("and ");
            }
        }
        if (tens != 0) {
            if (tens <= 20) {
                stringBuilder.append(numberToWord.get(tens));
                stringBuilder.append(" ");
            } else {
                units = tens % 10;
                tens = tens / 10 * 10;
                stringBuilder.append(numberToWord.get(tens));
                stringBuilder.append("-");
                stringBuilder.append(numberToWord.get(units));
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private static boolean isValidInput(String usersInput) {
        if (isNumber(usersInput)) {
            long input = Long.parseLong(usersInput);
            return (Math.abs(input) < Math.pow(10, 12));
        }
        return true;
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

    private static void printMenu() {
        System.out.println("Please, input number:");
    }

    private static String skipEscapes(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        while (stringBuilder.charAt(0) == ' ') {
            stringBuilder.delete(0, 1);
        }
        while (stringBuilder.charAt(stringBuilder.length() - 1) == ' ') {
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        }
        return stringBuilder.toString();
    }
}
