package com.github.anastasyann.numberconverter;

import java.util.HashMap;
import java.util.Map;

public class NumberToWordConverter {

    private final static Map<Long, String> numberToWord = new HashMap<>();

    static  {
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

    public static String getWordsFromNumber(Long number) {

        if (number == 0) {
            return numberToWord.get(0L);
        }

        StringBuilder stringBuilder = new StringBuilder();

        if (number < 0) {
            stringBuilder.append("minus ");
            number = Math.abs(number);
        }

        long billions = number / 1_000_000_000;
        if (billions != 0) {
            stringBuilder.append(getWordsFromNumberLessThan1000(billions));
            stringBuilder.append(numberToWord.get(1_000_000_000L));
            stringBuilder.append(" ");
        }

        long millions = (number % 1_000_000_000) / 1_000_000;
        if (millions != 0) {
            stringBuilder.append(getWordsFromNumberLessThan1000(millions));
            stringBuilder.append(numberToWord.get(1_000_000L));
            stringBuilder.append(" ");
        }

        long thousands = (number % 1_000_000) / 1_000;
        if (thousands != 0) {
            stringBuilder.append(getWordsFromNumberLessThan1000(thousands));
            stringBuilder.append(numberToWord.get(1000L));
            stringBuilder.append(" ");
        }

        long hundreds = number % 1_000;
        if (hundreds != 0) {
            stringBuilder.append(getWordsFromNumberLessThan1000(hundreds));
        }

        return stringBuilder.toString();
    }

    private static String getWordsFromNumberLessThan1000(Long number) {
        StringBuilder stringBuilder = new StringBuilder();

        long hundreds = number / 100;
        long tensWithUnits = number % 100;
        long units = tensWithUnits % 10;
        long tens = tensWithUnits / 10;

        if (hundreds != 0) {
            stringBuilder.append(numberToWord.get(hundreds));
            stringBuilder.append(" ");
            stringBuilder.append(numberToWord.get(100L));
            stringBuilder.append(" ");
            if (tensWithUnits != 0) {
                stringBuilder.append("and ");
            }
        }

        if (tensWithUnits == 0) {
            return stringBuilder.toString();
        }

        if (tensWithUnits <= 20) {
            stringBuilder.append(numberToWord.get(tensWithUnits));
            stringBuilder.append(" ");
        } else {
            stringBuilder.append(numberToWord.get(tens * 10));

            if (units != 0) {
                stringBuilder.append("-");
                stringBuilder.append(numberToWord.get(units));
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
