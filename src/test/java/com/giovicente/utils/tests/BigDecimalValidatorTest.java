package com.giovicente.utils.tests;

import com.giovicente.utils.BigDecimalSquareRoot;
import com.giovicente.utils.BigDecimalValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldThrowIllegalStateExceptionWhenAttemptingToInstantiate() throws NoSuchMethodException {
        Constructor<BigDecimalValidator> constructor = BigDecimalValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);

        assertTrue(exception.getCause() instanceof IllegalStateException);
        assertEquals("This class can't be instantiated", exception.getCause().getMessage());
    }
}
