package com.github.anastasyann.numberconverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordExtractor {

    private static final Pattern BILLION_EXTRACTOR_REGEX = Pattern.compile("^([a-zA-Z\\- ]+) billion");
    private static final Pattern MILLION_EXTRACTOR_REGEX = Pattern.compile("(^[a-zA-Z- ]+( billion)( |$)|^)(((|^)[a-zA-Z- ]+)( million)( |$)|^)([a-zA-Z\\- ]+(?<!million|billion).)*$");
    private static final Pattern THOUSAND_EXTRACTOR_REGEX = Pattern.compile("(^[a-zA-Z- ]+( million)( |$)|^)(((|^)[a-zA-Z- ]+)( thousand)( |$)|^)([a-zA-Z\\- ]+(?<!million|billion).)*$");
    private static final Pattern UNITS_EXTRACTOR_REGEX = Pattern.compile("(^[a-zA-Z- ]+(thousand|million|billion)( |$)|^)([a-zA-Z\\- ]+(?<!thousand|million|billion).)*$");

    //private static final Pattern REGEX = Pattern.compile("^([a-zA-Z\\- ]+) billion( |$)|([a-zA-Z\\- ]+) million( |$)|([a-zA-Z\\- ]+) thousand( |$)|([a-zA-Z\\- ]+)$");

    private static String applyRegexAndReturnChosenGroup(String sourceString, Pattern regex, int groupInd) {
        Matcher matcher = regex.matcher(sourceString);

        if (matcher.find()) {
            try {
                return (matcher.group(groupInd) != null) ? matcher.group(groupInd) : "";
            } catch (IndexOutOfBoundsException ex) {
                return "";
            }
        }

        return "";
    }

    public static String fetchBillionValue(String sourceString) {
        return applyRegexAndReturnChosenGroup(sourceString, BILLION_EXTRACTOR_REGEX, 1);
    }

    public static String fetchMillionValue(String sourceString) {
        return applyRegexAndReturnChosenGroup(sourceString, MILLION_EXTRACTOR_REGEX, 5);
    }

    public static String fetchThousandValue(String sourceString) {
        return applyRegexAndReturnChosenGroup(sourceString, THOUSAND_EXTRACTOR_REGEX, 5);
    }

    public static String fetchUnitsValue(String sourceString) {
        return applyRegexAndReturnChosenGroup(sourceString, UNITS_EXTRACTOR_REGEX, 4);
    }

}
