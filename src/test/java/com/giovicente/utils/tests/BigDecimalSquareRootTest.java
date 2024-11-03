package com.giovicente.utils.tests;

import com.giovicente.utils.BigDecimalSquareRoot;
import com.giovicente.utils.BigDecimalValidator;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BigDecimalSquareRootTest {

    @Test
    void shouldCalculateSquareRootOfPerfectSquare() {
        BigDecimal input = new BigDecimal("16");
        MathContext precisionContext = new MathContext(2);
        BigDecimal expected = new BigDecimal("4.0");

        BigDecimal actual = BigDecimalSquareRoot.sqrt(input, precisionContext);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateSquareRootOfNonPerfectSquare() {
        BigDecimal input = new BigDecimal("2");
        MathContext precisionContext = new MathContext(10);
        BigDecimal expected = new BigDecimal("1.414213563").setScale(9, RoundingMode.HALF_UP);

        BigDecimal actual = BigDecimalSquareRoot.sqrt(input, precisionContext);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateSquareRootOfNonPerfectDecimalNumberSquare() {
        BigDecimal input = setUpSpecialConditionToCalculateSquareRootOfNonPerfectDecimalNumberSquare();

        MathContext precisionContext = MathContext.DECIMAL128;
        BigDecimal expected = new BigDecimal("3.1").setScale(1, RoundingMode.HALF_UP);

        BigDecimal actual = new BigDecimal(
                String.valueOf(BigDecimalSquareRoot.sqrt(input, precisionContext))
        ).setScale(1, RoundingMode.HALF_UP);

        assertEquals(expected, actual);
    }

    @NotNull
    private static BigDecimal setUpSpecialConditionToCalculateSquareRootOfNonPerfectDecimalNumberSquare() {
        BigDecimal deviationProjectOne = new BigDecimal("1.8");
        BigDecimal deviationProjectTwo = new BigDecimal("2.2");
        BigDecimal deviationProjectThree = new BigDecimal("1.3");

        List<BigDecimal> deviations = Arrays.asList(deviationProjectOne, deviationProjectTwo, deviationProjectThree);

        BigDecimal input = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        return deviations.stream()
                .filter(BigDecimalValidator::isPositiveNumeric)
                .map(deviation -> deviation.multiply(deviation))
                .reduce(input, BigDecimal::add);
    }

    @Test
    void shouldReturnZeroForSquareRootOfZero() {
        BigDecimal input = BigDecimal.ZERO;
        MathContext precisionContext = new MathContext(10);

        BigDecimal actual = BigDecimalSquareRoot.sqrt(input, precisionContext);

        assertEquals(input, actual);
    }

    @Test
    void shouldReturnOneForSquareRootOfOne() {
        BigDecimal input = BigDecimal.ONE;
        MathContext precisionContext = new MathContext(10);

        BigDecimal actual = BigDecimalSquareRoot.sqrt(input, precisionContext);

        assertEquals(input, actual);
    }

    @Test
    void shouldThrowExceptionForNegativeNumbers() {
        BigDecimal input = new BigDecimal("-4");
        MathContext precisionContext = new MathContext(10);

        assertThrows(ArithmeticException.class, () -> {
            BigDecimalSquareRoot.sqrt(input, precisionContext);
        });
    }


    @Test
    void shouldRespectPrecisionForSquareRootCalculation() {
        BigDecimal input = new BigDecimal("10");
        MathContext precisionContext = new MathContext(5);
        BigDecimal expected = new BigDecimal("3.1623").setScale(4, RoundingMode.HALF_UP);

        BigDecimal actual = BigDecimalSquareRoot.sqrt(input, precisionContext);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateSquareRootOfLargeNumber() {
        BigDecimal input = new BigDecimal("1000000000000");
        MathContext precisionContext = new MathContext(7);
        BigDecimal expected = new BigDecimal("1000000");

        BigDecimal actual = BigDecimalSquareRoot.sqrt(input, precisionContext);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateSquareRootOfSmallNumber() {
        BigDecimal input = new BigDecimal("0.0002");
        MathContext precisionContext = new MathContext(1);
        BigDecimal expected = new BigDecimal("0.02");

        BigDecimal actual = BigDecimalSquareRoot.sqrt(input, precisionContext);

        assertEquals(expected, actual);
    }
}
