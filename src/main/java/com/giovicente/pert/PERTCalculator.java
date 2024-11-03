package com.giovicente.pert;

import com.giovicente.utils.BigDecimalSquareRoot;
import com.giovicente.utils.BigDecimalValidator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class PERTCalculator {

    private PERTCalculator() {
        throw new IllegalStateException("This class can't be instantiated");
    }

    public static BigDecimal calculateDuration(BigDecimal optimistic, BigDecimal nominal, BigDecimal pessimistic) {
        BigDecimal weightedEstimate = new BigDecimal("0");

        if (BigDecimalValidator.isPositiveNumeric(optimistic) &&
                BigDecimalValidator.isPositiveNumeric(nominal) &&
                BigDecimalValidator.isPositiveNumeric(pessimistic)) {

            BigDecimal weightedNominalEstimate = nominal.multiply(new BigDecimal("4"));
            weightedEstimate = optimistic.add(weightedNominalEstimate).add(pessimistic);
        }

        return weightedEstimate.divide(new BigDecimal("6"), 1, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateStandardDeviation(BigDecimal pessimistic, BigDecimal optimistic) {
        BigDecimal weightedTaskUncertainty = new BigDecimal("0");

        if (BigDecimalValidator.isPositiveNumeric(pessimistic)
                && BigDecimalValidator.isPositiveNumeric(optimistic)) {
            weightedTaskUncertainty = pessimistic.subtract(optimistic);
        }

        return weightedTaskUncertainty.divide(new BigDecimal("6"), 1, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateProbabilityDistributionDurations(List<BigDecimal> durations) {
        BigDecimal sumOfDurations = BigDecimal.ZERO.setScale(1, RoundingMode.HALF_UP);

        return durations.stream()
                .filter(BigDecimalValidator::isPositiveNumeric)
                .reduce(sumOfDurations, BigDecimal::add);
    }

    public static BigDecimal calculateProbabilityDistributionStandardDeviations(List<BigDecimal> deviations) {
        BigDecimal sumOfDeviations = BigDecimal.ZERO.setScale(1, RoundingMode.HALF_UP);

        sumOfDeviations = deviations.stream()
                .filter(BigDecimalValidator::isPositiveNumeric)
                .map(deviation -> deviation.multiply(deviation))
                .reduce(sumOfDeviations, BigDecimal::add);

        return new BigDecimal(
                String.valueOf(
                        BigDecimalSquareRoot.sqrt(sumOfDeviations, MathContext.DECIMAL128)
                )
        ).setScale(1, RoundingMode.HALF_UP);
    }
}
