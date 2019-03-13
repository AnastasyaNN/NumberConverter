package com.github.anastasyann.numberconverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(BlockJUnit4ClassRunner.class)
public class NumberToWordConverterTest {

    @Test
    public void getWordsFromNumber_test() {
        String result = NumberToWordConverter.getWordsFromNumber(0L);
        assertThat(result, is("zero"));

        result = NumberToWordConverter.getWordsFromNumber(111L);
        assertThat(result, is("one hundred and eleven "));

        result = NumberToWordConverter.getWordsFromNumber(131L);
        assertThat(result, is("one hundred and thirty-one "));

        result = NumberToWordConverter.getWordsFromNumber(2222L);
        assertThat(result, is("two thousand two hundred and twenty-two "));

        result = NumberToWordConverter.getWordsFromNumber(123_456_789L);
        assertThat(result, is("one hundred and twenty-three million four hundred and fifty-six thousand seven hundred and eighty-nine "));

        result = NumberToWordConverter.getWordsFromNumber(978_645_312_000L);
        assertThat(result, is("nine hundred and seventy-eight billion six hundred and forty-five million three hundred and twelve thousand "));

        result = NumberToWordConverter.getWordsFromNumber(-978_645_312_569L);
        assertThat(result, is("minus nine hundred and seventy-eight billion six hundred and forty-five million three hundred and twelve thousand five hundred and sixty-nine "));
    }

}
