package com.github.anastasyann.numberconverter;

import java.util.HashMap;
import java.util.Map;

public class WordToNumberConverter {

    private final static Map<String, Long> wordToNumber = new HashMap<>();
    private final static Map<Integer, String> numberDegreeToWord = new HashMap<>();

    static {
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

        numberDegreeToWord.put(3,"thousand");
        numberDegreeToWord.put(6,"million");
        numberDegreeToWord.put(9,"billion");
    }

    public static Long getNumberFromWords(String wordInput) {
        wordInput = wordInput.trim();
        boolean isNegative = false;

        if (wordInput.equals("zero")) {
            return wordToNumber.get("zero");
        }

        if (wordInput.contains("minus")) {
            isNegative = true;
            wordInput = wordInput.substring("minus ".length());
        }

        Long number = 0L;

        String extractedBillionValue = WordExtractor.fetchBillionValue(wordInput);
        if (!extractedBillionValue.equals("")) {
            Long currentNumber = getNumberLessThan1000FromWords(extractedBillionValue);
            if (currentNumber == Long.MAX_VALUE) {
                return currentNumber;
            }
            number = currentNumber * 1_000_000_000;
        }

        String extractedMillionValue = WordExtractor.fetchMillionValue(wordInput);
        if (!extractedMillionValue.equals("")) {
            Long currentNumber = getNumberLessThan1000FromWords(extractedMillionValue);
            if (currentNumber == Long.MAX_VALUE) {
                return currentNumber;
            }
            number += currentNumber * 1_000_000;
        }

        String extractedThousandValue = WordExtractor.fetchThousandValue(wordInput);
        if (!extractedThousandValue.equals("")) {
            Long currentNumber = getNumberLessThan1000FromWords(extractedThousandValue);
            if (currentNumber == Long.MAX_VALUE) {
                return currentNumber;
            }
            number += currentNumber * 1_000;
        }

        String extractedUnitsValue = WordExtractor.fetchUnitsValue(wordInput);
        if (!extractedUnitsValue.equals("")) {
            Long currentNumber = getNumberLessThan1000FromWords(extractedUnitsValue);
            if (currentNumber == Long.MAX_VALUE) {
                return currentNumber;
            }
            number += currentNumber;
        }

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
}
