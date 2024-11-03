package com.giovicente.utils.tests;

import com.giovicente.utils.BigDecimalValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class BigDecimalValidatorTest {

    @Test
    void shouldReturnTrueToValidBigDecimal() {
        Assertions.assertTrue(BigDecimalValidator.isPositiveNumeric(new BigDecimal("1.8")));
    }

    @Test
    void shouldThrowNumberFormatExceptionToInvalidBigDecimal() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            new BigDecimal("e");
        });
    }

    @Test
    void shouldThrowNumberFormatExceptionToNegativeBigDecimal() {
        BigDecimal input = new BigDecimal("-1");

        Assertions.assertThrows(NumberFormatException.class, () -> {
            BigDecimalValidator.isPositiveNumeric(input);
        });
    }

    @Test
    void shouldReturnTrueToZeroBigDecimal() {
        Assertions.assertTrue(BigDecimalValidator.isPositiveNumeric(BigDecimal.ZERO));
    }
}
