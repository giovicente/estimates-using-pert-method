package com.giovicente.pert.tests;

import com.giovicente.utils.BigDecimalValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.giovicente.pert.PERTCalculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PERTCalculatorTest {

    private static BigDecimal optimistic;
    private static BigDecimal realistic;
    private static BigDecimal pessimistic;
    private static List<BigDecimal> durations;
    private static List<BigDecimal> deviations;

    @BeforeAll
    static void setUp() {
        optimistic = BigDecimal.ONE;
        realistic  = new BigDecimal("3");
        pessimistic = new BigDecimal("12");

        BigDecimal durationProjectOne = new BigDecimal("4.2");
        BigDecimal durationProjectTwo = new BigDecimal("3.5");
        BigDecimal durationProjectThree = new BigDecimal("6.5");

        BigDecimal deviationProjectOne = new BigDecimal("1.8");
        BigDecimal deviationProjectTwo = new BigDecimal("2.2");
        BigDecimal deviationProjectThree = new BigDecimal("1.3");

        durations = Arrays.asList(durationProjectOne, durationProjectTwo, durationProjectThree);
        deviations = Arrays.asList(deviationProjectOne, deviationProjectTwo, deviationProjectThree);
    }

    @Test
    void shouldEstimateCorrectDuration() {
        BigDecimal expectedDuration = new BigDecimal("4.2");
        BigDecimal actualDuration = new BigDecimal(
                String.valueOf(PERTCalculator.calculateDuration(optimistic, realistic, pessimistic))
        );

        Assertions.assertEquals(expectedDuration, actualDuration);
    }

    @Test
    void shouldReturnCorrectStandardDeviation() {
        BigDecimal expectedStandardDeviation = new BigDecimal("1.8");
        BigDecimal actualStandardDeviation = new BigDecimal(
                String.valueOf(PERTCalculator.calculateStandardDeviation(pessimistic, optimistic))
        );

        Assertions.assertEquals(expectedStandardDeviation, actualStandardDeviation);
    }

    @Test
    void shouldReturnCorrectSumOfDurations() {
        BigDecimal expectedSumOfDurations = new BigDecimal("14.2");
        BigDecimal actualSumOfDurations = new BigDecimal(
                String.valueOf(PERTCalculator.calculateProbabilityDistributionDurations(durations))
        );

        Assertions.assertEquals(expectedSumOfDurations, actualSumOfDurations);
    }

    @Test
    void shouldReturnCorrectDeviations() {
        BigDecimal expectedSumOfDeviations = new BigDecimal("3.1");
        BigDecimal actualSumOfDeviations = new BigDecimal(
                String.valueOf(PERTCalculator.calculateProbabilityDistributionStandardDeviations(deviations))
        );

        Assertions.assertEquals(expectedSumOfDeviations, actualSumOfDeviations);
    }
}