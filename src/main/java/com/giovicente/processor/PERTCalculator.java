package com.giovicente.processor;

import com.giovicente.utils.BigDecimalSquareRootImpl;
import com.giovicente.utils.BigDecimalValidatorImpl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class PERTCalculator {

    private static BigDecimalValidator validator;

    private PERTCalculator() {
        throw new IllegalStateException("This class can't be instantiated");
    }

    public static BigDecimal calculateDuration(BigDecimal optimistic, BigDecimal nominal, BigDecimal pessimistic) {
        BigDecimal weightedEstimate = new BigDecimal("0");
        validator = new BigDecimalValidatorImpl();

        if (validator.isPositiveNumeric(optimistic) &&
                validator.isPositiveNumeric(nominal) &&
                validator.isPositiveNumeric(pessimistic)) {

            BigDecimal weightedNominalEstimate = nominal.multiply(new BigDecimal("4"));
            weightedEstimate = optimistic.add(weightedNominalEstimate).add(pessimistic);
        }

        return weightedEstimate.divide(new BigDecimal("6"), 1, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateStandardDeviation(BigDecimal pessimistic, BigDecimal optimistic) {
        BigDecimal weightedTaskUncertainty = new BigDecimal("0");
        validator = new BigDecimalValidatorImpl();

        if (validator.isPositiveNumeric(pessimistic)
                && validator.isPositiveNumeric(optimistic)) {
            weightedTaskUncertainty = pessimistic.subtract(optimistic);
        }

        return weightedTaskUncertainty.divide(new BigDecimal("6"), 1, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateProbabilityDistributionDurations(List<BigDecimal> durations) {
        BigDecimal sumOfDurations = BigDecimal.ZERO.setScale(1, RoundingMode.HALF_UP);
        validator = new BigDecimalValidatorImpl();

        return durations.stream()
                .filter(validator::isPositiveNumeric)
                .reduce(sumOfDurations, BigDecimal::add);
    }

    public static BigDecimal calculateProbabilityDistributionStandardDeviations(List<BigDecimal> deviations) {
        BigDecimal sumOfDeviations = BigDecimal.ZERO.setScale(1, RoundingMode.HALF_UP);
        BigDecimalSquareRoot squareRoot = new BigDecimalSquareRootImpl();
        validator = new BigDecimalValidatorImpl();

        sumOfDeviations = deviations.stream()
                .filter(validator::isPositiveNumeric)
                .map(deviation -> deviation.multiply(deviation))
                .reduce(sumOfDeviations, BigDecimal::add);

        return new BigDecimal(
                String.valueOf(
                        squareRoot.sqrt(sumOfDeviations, MathContext.DECIMAL128)
                )
        ).setScale(1, RoundingMode.HALF_UP);
    }
}
