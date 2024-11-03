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
            BigDecimalValidator.isPositiveNumeric(new BigDecimal("e"));
        });
    }

    @Test
    void shouldThrowNumberFormatExceptionToNegativeBigDecimal() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            BigDecimalValidator.isPositiveNumeric(new BigDecimal("-1"));
        });
    }

    @Test
    void shouldReturnTrueToZeroBigDecimal() {
        Assertions.assertTrue(BigDecimalValidator.isPositiveNumeric(BigDecimal.ZERO));
    }
}
