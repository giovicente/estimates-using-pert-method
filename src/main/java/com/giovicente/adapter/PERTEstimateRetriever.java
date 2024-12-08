package com.giovicente.adapter;

import com.giovicente.processor.PERTCalculator;
import com.giovicente.processor.Printer;
import com.giovicente.utils.PrinterImpl;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Scanner;

public class PERTEstimateRetriever {

    private static Printer printer;

    private PERTEstimateRetriever() {
        throw new IllegalStateException("This class can't be instantiated");
    }

    @NotNull
    public static BigDecimal getOptimistic(Scanner pertScanner) {
        printer = new PrinterImpl();
        printer.printInsertOptimisticEstimate();
        return new BigDecimal(String.valueOf(pertScanner.nextBigDecimal()));
    }

    @NotNull
    public static BigDecimal getNominal(Scanner pertScanner) {
        printer = new PrinterImpl();
        printer.printInsertNominalEstimate();
        return new BigDecimal(String.valueOf(pertScanner.nextBigDecimal()));
    }

    @NotNull
    public static BigDecimal getPessimistic(Scanner pertScanner) {
        printer = new PrinterImpl();
        printer.printInsertPessimisticEstimate();
        return new BigDecimal(String.valueOf(pertScanner.nextBigDecimal()));
    }

    @NotNull
    public static BigDecimal getDuration(BigDecimal optimistic, BigDecimal nominal, BigDecimal pessimistic) {
        printer = new PrinterImpl();

        BigDecimal duration = new BigDecimal(
                String.valueOf(PERTCalculator.calculateDuration(optimistic, nominal, pessimistic))
        );

        printer.printDuration(duration);
        return duration;
    }

    @NotNull
    public static BigDecimal getIndividualStandardDeviation(BigDecimal optimistic, BigDecimal pessimistic) {
        printer = new PrinterImpl();

        BigDecimal standardDeviation = new BigDecimal(
                String.valueOf(PERTCalculator.calculateStandardDeviation(pessimistic, optimistic))
        );

        printer.printStandardDeviation(standardDeviation);
        return standardDeviation;
    }

    @NotNull
    public static BigDecimal getIndividualEstimate(BigDecimal optimistic, BigDecimal nominal, BigDecimal pessimistic) {
        printer = new PrinterImpl();

        printer.printAsterisks();

        BigDecimal duration = getDuration(optimistic, nominal, pessimistic);
        BigDecimal standardDeviation = getIndividualStandardDeviation(optimistic, pessimistic);

        return duration.add(standardDeviation);
    }
}
