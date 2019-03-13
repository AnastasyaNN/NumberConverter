package com.github.anastasyann.numberconverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(BlockJUnit4ClassRunner.class)
public class WordToNumberConverterTest {
    @Test
    public void getNumberFromWords_test() {
        Long result = WordToNumberConverter.getNumberFromWords("zero");
        assertThat(result, is(0L));

        result = WordToNumberConverter.getNumberFromWords("minus one hundred and eleven");
        assertThat(result, is(-111L));

        result = WordToNumberConverter.getNumberFromWords("one hundred and thirty-one");
        assertThat(result, is(131L));

        result = WordToNumberConverter.getNumberFromWords("two thousand two hundred and twenty-two");
        assertThat(result, is(2222L));

        result = WordToNumberConverter.getNumberFromWords("one hundred and twenty-three million four hundred and fifty-six thousand seven hundred and eighty-nine");
        assertThat(result, is(123_456_789L));

        result = WordToNumberConverter.getNumberFromWords("nine hundred and seventy-eight billion six hundred and forty-five million three hundred and twelve thousand");
        assertThat(result, is(978_645_312_000L));

        result = WordToNumberConverter.getNumberFromWords("minus nine hundred and seventy-eight billion six hundred and forty-five million three hundred and twelve thousand five hundred and sixty-nine");
        assertThat(result, is(-978_645_312_569L));

        result = WordToNumberConverter.getNumberFromWords("minus nine hundred and seventy-eight billion six hundred and forty-five million three hundred and twelve thousand five hundred and sixty-ninee");
        assertThat(result, is(Long.MAX_VALUE));
    }
}
