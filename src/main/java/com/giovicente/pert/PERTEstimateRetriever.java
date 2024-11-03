package com.giovicente.pert;

import com.giovicente.utils.Printer;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Scanner;

public class PERTEstimateRetriever {

    private PERTEstimateRetriever() {
        throw new IllegalStateException("This class can't be instantiated");
    }

    @NotNull
    static BigDecimal getOptimistic(Scanner pertScanner) {
        Printer.printInsertOptimisticEstimate();
        return new BigDecimal(String.valueOf(pertScanner.nextBigDecimal()));
    }

    @NotNull
    static BigDecimal getNominal(Scanner pertScanner) {
        Printer.printInsertNominalEstimate();
        return new BigDecimal(String.valueOf(pertScanner.nextBigDecimal()));
    }

    @NotNull
    static BigDecimal getPessimistic(Scanner pertScanner) {
        Printer.printInsertPessimisticEstimate();
        return new BigDecimal(String.valueOf(pertScanner.nextBigDecimal()));
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

    @NotNull
    static BigDecimal getIndividualEstimate(BigDecimal optimistic, BigDecimal nominal, BigDecimal pessimistic) {
        Printer.printAsterisks();

        BigDecimal duration = getDuration(optimistic, nominal, pessimistic);
        BigDecimal standardDeviation = getIndividualStandardDeviation(optimistic, pessimistic);

        return duration.add(standardDeviation);
    }
}
