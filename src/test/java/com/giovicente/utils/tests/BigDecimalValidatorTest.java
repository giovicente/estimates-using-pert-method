package com.giovicente.utils.tests;

import com.giovicente.utils.BigDecimalValidatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.giovicente.processor.BigDecimalValidator;

import java.math.BigDecimal;

class BigDecimalValidatorTest {

    private static BigDecimalValidator validator;

    @BeforeAll
    static void setUp() {
        validator = new BigDecimalValidatorImpl();
    }

    @Test
    void shouldReturnTrueToValidBigDecimal() {
        Assertions.assertTrue(validator.isPositiveNumeric(new BigDecimal("1.8")));
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
            validator.isPositiveNumeric(input);
        });
    }

    @Test
    void shouldReturnTrueToZeroBigDecimal() {
        Assertions.assertTrue(validator.isPositiveNumeric(BigDecimal.ZERO));
    }
}
