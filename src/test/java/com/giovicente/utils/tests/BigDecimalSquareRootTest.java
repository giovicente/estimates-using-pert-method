package com.giovicente.utils.tests;

import com.giovicente.utils.BigDecimalSquareRootImpl;
import com.giovicente.utils.BigDecimalValidatorImpl;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.giovicente.processor.BigDecimalSquareRoot;
import com.giovicente.processor.BigDecimalValidator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalSquareRootTest {

    private static BigDecimalSquareRoot squareRoot;
    private static BigDecimalValidator validator;

    @BeforeAll
    static void setUp() {
        squareRoot = new BigDecimalSquareRootImpl();
    }

    @ParameterizedTest
    @CsvSource({
            "16, 2, 4.0",
            "2, 10, 1.414213563"
    })
    void shouldCalculateSquareRootCorrectly(BigDecimal input, int precision, BigDecimal expected) {
        MathContext precisionContext = new MathContext(precision);
        BigDecimal actual = squareRoot.sqrt(input, precisionContext);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateSquareRootOfNonPerfectDecimalNumberSquare() {
        BigDecimal input = setUpSpecialConditionToCalculateSquareRootOfNonPerfectDecimalNumberSquare();

        MathContext precisionContext = MathContext.DECIMAL128;
        BigDecimal expected = new BigDecimal("3.1").setScale(1, RoundingMode.HALF_UP);

        BigDecimal actual = new BigDecimal(
                String.valueOf(squareRoot.sqrt(input, precisionContext))
        ).setScale(1, RoundingMode.HALF_UP);

        assertEquals(expected, actual);
    }

    @NotNull
    private static BigDecimal setUpSpecialConditionToCalculateSquareRootOfNonPerfectDecimalNumberSquare() {
        validator = new BigDecimalValidatorImpl();

        BigDecimal deviationProjectOne = new BigDecimal("1.8");
        BigDecimal deviationProjectTwo = new BigDecimal("2.2");
        BigDecimal deviationProjectThree = new BigDecimal("1.3");

        List<BigDecimal> deviations = Arrays.asList(deviationProjectOne, deviationProjectTwo, deviationProjectThree);

        BigDecimal input = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        return deviations.stream()
                .filter(validator::isPositiveNumeric)
                .map(deviation -> deviation.multiply(deviation))
                .reduce(input, BigDecimal::add);
    }

    @Test
    void shouldReturnZeroForSquareRootOfZero() {
        BigDecimal input = BigDecimal.ZERO;
        MathContext precisionContext = new MathContext(10);

        BigDecimal actual = squareRoot.sqrt(input, precisionContext);

        assertEquals(input, actual);
    }

    @Test
    void shouldReturnOneForSquareRootOfOne() {
        BigDecimal input = BigDecimal.ONE;
        MathContext precisionContext = new MathContext(10);

        BigDecimal actual = squareRoot.sqrt(input, precisionContext);

        assertEquals(input, actual);
    }

    @Test
    void shouldThrowExceptionForNegativeNumbers() {
        BigDecimal input = new BigDecimal("-4");
        MathContext precisionContext = new MathContext(10);

        assertThrows(ArithmeticException.class, () -> {
            squareRoot.sqrt(input, precisionContext);
        });
    }


    @Test
    void shouldRespectPrecisionForSquareRootCalculation() {
        BigDecimal input = new BigDecimal("10");
        MathContext precisionContext = new MathContext(5);
        BigDecimal expected = new BigDecimal("3.1623").setScale(4, RoundingMode.HALF_UP);

        BigDecimal actual = squareRoot.sqrt(input, precisionContext);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateSquareRootOfLargeNumber() {
        BigDecimal input = new BigDecimal("1000000000000");
        MathContext precisionContext = new MathContext(7);
        BigDecimal expected = new BigDecimal("1000000");

        BigDecimal actual = squareRoot.sqrt(input, precisionContext);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateSquareRootOfSmallNumber() {
        BigDecimal input = new BigDecimal("0.0002");
        MathContext precisionContext = new MathContext(1);
        BigDecimal expected = new BigDecimal("0.02");

        BigDecimal actual = squareRoot.sqrt(input, precisionContext);

        assertEquals(expected, actual);
    }
}
