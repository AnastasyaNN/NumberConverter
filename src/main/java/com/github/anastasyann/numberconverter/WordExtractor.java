package com.github.anastasyann.numberconverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordExtractor {

    private static final Pattern BILLION_EXTRACTOR_REGEX = Pattern.compile("^([a-zA-Z\\- ]+) billion");
    private static final Pattern UNITS_EXTRACTOR_REGEX = Pattern.compile("(^[a-zA-Z- ]+(thousand|million|billion) |^)([a-zA-Z\\- ]+)$");

    public static String fetchBillionValue(String sourceString) {
        return applyRegexAndReturnChosenGroup(sourceString, BILLION_EXTRACTOR_REGEX, 1);
    }

    public static String fetchUnitsValue(String sourceString) {
        return applyRegexAndReturnChosenGroup(sourceString, UNITS_EXTRACTOR_REGEX, 3);
    }

    private static String applyRegexAndReturnChosenGroup(String sourceString, Pattern regex, int groupInd) {
        Matcher matcher = regex.matcher(sourceString);
        if (matcher.find()) {
            return matcher.group(groupInd);
        }

        return null;
    }
}
