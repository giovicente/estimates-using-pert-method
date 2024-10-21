package org.example;

import com.giovicente.pert.PERTCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BigDecimal optimistic = new BigDecimal("1");
        BigDecimal realistic = new BigDecimal("3");
        BigDecimal pessimistic = new BigDecimal("12");

        List<BigDecimal> deviations;

        BigDecimal deviationProjectOne = new BigDecimal("1.8");
        BigDecimal deviationProjectTwo = new BigDecimal("2.2");
        BigDecimal deviationProjectThree = new BigDecimal("1.3");
        BigDecimal invalid = new BigDecimal("-1");

        deviations = Arrays.asList(deviationProjectOne, deviationProjectTwo, deviationProjectThree);

        System.out.print(
                "μ = " + new BigDecimal(String.valueOf(PERTCalculator.calculateDuration(optimistic, realistic, pessimistic))) + " "
        );

        System.out.print(
                "σ = " + new BigDecimal(String.valueOf(PERTCalculator.calculateStandardDeviation(pessimistic, optimistic))) + " "
        );

        System.out.print(
                "Σμ = " + new BigDecimal(String.valueOf(PERTCalculator.calculateProbabilityDistributionDurations(new ArrayList<>()))) + " "
        );

        System.out.print(
                "√Σ(σ²) = " + new BigDecimal(String.valueOf(PERTCalculator.calculateProbabilityDistributionStandardDeviations(deviations)))
        );
    }
}
