package com.giovicente.utils.tests;

import com.giovicente.utils.BigDecimalValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BigDecimalValidatorTest {

    @Test
    public void shouldReturnTrueToValidBigDecimal() {
        Assertions.assertTrue(BigDecimalValidator.isPositiveNumeric(new BigDecimal("1.8")));
    }

    @Test
    public void shouldThrowNumberFormatExceptionToInvalidBigDecimal() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            BigDecimalValidator.isPositiveNumeric(new BigDecimal("e"));
        });
    }

    @Test
    public void shouldThrowNumberFormatExceptionToNegativeBigDecimal() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            BigDecimalValidator.isPositiveNumeric(new BigDecimal("-1"));
        });
    }

    @Test
    public void shouldReturnTrueToZeroBigDecimal() {
        Assertions.assertTrue(BigDecimalValidator.isPositiveNumeric(BigDecimal.ZERO));
    }
}
