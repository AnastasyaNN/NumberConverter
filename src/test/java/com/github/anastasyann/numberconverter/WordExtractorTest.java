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
    }

    @Test
    public void fetchUnitsValue_test() {
        String result = WordExtractor.fetchUnitsValue(
                "one hundred and twenty-three billion four hundred and fifty-six million seven hundred and eighty-nine thousand ninety-eight"
        );
        assertThat(result, is("ninety-eight"));

        result = WordExtractor.fetchUnitsValue(
                "one hundred and twenty-three billion four hundred and fifty-six million seven hundred and eighty-nine thousand one hundred and ninety-eight"
        );
        assertThat(result, is("one hundred and ninety-eight"));
    }
}
