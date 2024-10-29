package com.giovicente.pert;

import com.giovicente.utils.Printer;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Scanner;

public class PERTEstimateRetriever {

    @NotNull
    static BigDecimal getOptimistic(Scanner PERTScanner) {
        Printer.printInsertOptimisticEstimate();
        return new BigDecimal(String.valueOf(PERTScanner.nextBigDecimal()));
    }

    @NotNull
    static BigDecimal getNominal(Scanner PERTScanner) {
        Printer.printInsertNominalEstimate();
        return new BigDecimal(String.valueOf(PERTScanner.nextBigDecimal()));
    }

    @NotNull
    static BigDecimal getPessimistic(Scanner PERTScanner) {
        Printer.printInsertPessimisticEstimate();
        return new BigDecimal(String.valueOf(PERTScanner.nextBigDecimal()));
    }

    @NotNull
    static BigDecimal getDuration(BigDecimal optimistic, BigDecimal nominal, BigDecimal pessimistic) {
        BigDecimal duration;
        Printer.printDuration(duration = new BigDecimal(
                String.valueOf(PERTCalculator.calculateDuration(optimistic, nominal, pessimistic)))
        );
        return duration;
    }

    @NotNull
    static BigDecimal getIndividualStandardDeviation(BigDecimal optimistic, BigDecimal pessimistic) {
        BigDecimal standardDeviation;
        Printer.printStandardDeviation(standardDeviation = new BigDecimal(
                String.valueOf(PERTCalculator.calculateStandardDeviation(pessimistic, optimistic))
        ));
        return standardDeviation;
    }
}
