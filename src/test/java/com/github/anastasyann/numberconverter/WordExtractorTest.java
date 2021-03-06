package com.github.anastasyann.numberconverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class WordExtractorTest {

    @Test
    public void fetchBillionValue_test() {
        String result = WordExtractor.fetchBillionValue(
                "one hundred and twenty-three billion four hundred and fifty-six million seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is("one hundred and twenty-three"));

        result = WordExtractor.fetchBillionValue(
                "four hundred and fifty-six million seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is(""));
    }

    @Test
    public void fetchMillionValue_test() {
        String result = WordExtractor.fetchMillionValue(
                "one hundred and twenty-three billion four hundred and fifty-six million seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is("four hundred and fifty-six"));

        result = WordExtractor.fetchMillionValue(
                "four hundred and fifty-six million seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is("four hundred and fifty-six"));

        result = WordExtractor.fetchMillionValue(
                "four hundred and fifty-six million"
        );
        assertThat(result, is("four hundred and fifty-six"));

        result = WordExtractor.fetchMillionValue(
                "one hundred and twenty-three billion seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is(""));
    }

    @Test
    public void fetchThousandValue_test() {
        String result = WordExtractor.fetchThousandValue(
                "one hundred and twenty-three billion four hundred and fifty-six million seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is("seven hundred and eighty-nine"));

        result = WordExtractor.fetchThousandValue(
                "four hundred and fifty-six million seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is("seven hundred and eighty-nine"));

        result = WordExtractor.fetchThousandValue(
                "seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is("seven hundred and eighty-nine"));

        result = WordExtractor.fetchThousandValue(
                "one hundred and twenty-three billion four hundred and fifty-six million seven hundred and eighty-nine thousand"
        );
        assertThat(result, is("seven hundred and eighty-nine"));

        result = WordExtractor.fetchThousandValue(
                "seven hundred and eighty-nine thousand"
        );
        assertThat(result, is("seven hundred and eighty-nine"));

        result = WordExtractor.fetchThousandValue(
                "one hundred and twenty-three billion four hundred and fifty-six million seven hundred and ninety-eight"
        );
        assertThat(result, is(""));
    }

    @Test//(expected = IllegalArgumentException.class)
    public void fetchUnitsValue_test() {
        String result = WordExtractor.fetchUnitsValue(
                "one hundred and twenty-three billion four hundred and fifty-six million seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is("ninety-eight"));

        result = WordExtractor.fetchUnitsValue(
                "one hundred and twenty-three billion four hundred and fifty-six million seven hundred and eighty-nine thousand one hundred and ninety-eight"
        );
        assertThat(result, is("one hundred and ninety-eight"));

        result = WordExtractor.fetchUnitsValue(
                "four hundred and fifty-six million seven hundred and eighty-nine thousand one hundred and ninety-eight"
        );
        assertThat(result, is("one hundred and ninety-eight"));

        result = WordExtractor.fetchUnitsValue(
                "seven hundred and eighty-nine thousand one hundred and ninety-eight"
        );
        assertThat(result, is("one hundred and ninety-eight"));

        result = WordExtractor.fetchUnitsValue(
                "one hundred and ninety-eight"
        );
        assertThat(result, is("one hundred and ninety-eight"));

        result = WordExtractor.fetchUnitsValue(
                "ninety-eight"
        );
        assertThat(result, is("ninety-eight"));

        result = WordExtractor.fetchUnitsValue(
                "four hundred and fifty-six million seven hundred and eighty-nine thousand"
        );
        assertThat(result, is(""));

        result = WordExtractor.fetchUnitsValue(
                "four hundred and fifty-six million"
        );
        assertThat(result, is(""));

        result = WordExtractor.fetchUnitsValue(
                "four hundred and fifty-six billion"
        );
        assertThat(result, is(""));
    }
}
