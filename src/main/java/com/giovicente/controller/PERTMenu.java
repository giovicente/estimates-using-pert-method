package com.giovicente.controller;

import com.giovicente.processor.PERTCalculator;
import com.giovicente.processor.Printer;
import com.giovicente.utils.PrinterImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.giovicente.adapter.PERTEstimateRetriever.*;

public class PERTMenu {

    private PERTMenu() { throw new IllegalStateException("This class can't be instantiated"); }

    public static void runPERT() {
        Printer printer = new PrinterImpl();

        printer.printAsterisks();
        printer.printTitle();
        printer.printAsterisks();

        Scanner pertScanner = new Scanner(System.in);

        final int INDIVIDUAL_ESTIMATE = 1;
        final int BATCH_ESTIMATE = 2;

        BigDecimal optimistic;
        BigDecimal nominal;
        BigDecimal pessimistic;
        BigDecimal realistic;

        boolean isRunning = false;

        do {
            printer.printOptionMessage();
            int option = pertScanner.nextInt();
            printer.printAsterisks();

            while (option != INDIVIDUAL_ESTIMATE && option != BATCH_ESTIMATE) {
                printer.printInvalidOptionMessage();
                printer.printAsterisks();

                printer.printOptionMessage();
                option = pertScanner.nextInt();
                printer.printAsterisks();
            }

            if (option == INDIVIDUAL_ESTIMATE) {
                optimistic = getOptimistic(pertScanner);
                nominal = getNominal(pertScanner);
                pessimistic = getPessimistic(pertScanner);

                realistic = getIndividualEstimate(optimistic, nominal, pessimistic);
                printer.printRealisticEstimate(realistic);
                printer.printAsterisks();
            } else {
                List<BigDecimal> durations = new ArrayList<>();
                List<BigDecimal> deviations = new ArrayList<>();

                getBatchEstimate(pertScanner, durations, deviations);
                printer.printAsterisks();
            }

            char continuity = hasContinuity(pertScanner);
            isRunning = (continuity == 'Y');
        } while (isRunning);

        pertScanner.close();
    }

    public static char hasContinuity(Scanner pertScanner) {
        Printer printer = new PrinterImpl();

        printer.printContinuityMessage();
        char continuity = Character.toUpperCase(pertScanner.next().charAt(0));
        printer.printAsterisks();

        while (continuity != 'Y' && continuity != 'N') {
            printer.printInvalidContinuityMessage();
            printer.printAsterisks();

            printer.printContinuityMessage();
            continuity = Character.toUpperCase(pertScanner.next().charAt(0));
            printer.printAsterisks();
        }

        return continuity;
    }

    public static void getBatchEstimate(Scanner pertscanner, List<BigDecimal> durations, List<BigDecimal> deviations) {
        BigDecimal optimistic;
        BigDecimal pessimistic;
        boolean hasMoreEstimates;
        BigDecimal nominal;

        Printer printer = new PrinterImpl();

        do {
            optimistic = getOptimistic(pertscanner);
            nominal = getNominal(pertscanner);
            pessimistic = getPessimistic(pertscanner);
            printer.printAsterisks();

            BigDecimal duration = getDuration(optimistic, nominal, pessimistic);

            durations.add(duration);
            deviations.add(getIndividualStandardDeviation(optimistic, pessimistic));
            printer.printAsterisks();

            char continuity = hasContinuity(pertscanner);
            hasMoreEstimates = (continuity == 'Y');
        } while (hasMoreEstimates);

        BigDecimal weightedDuration = new BigDecimal(
                String.valueOf(PERTCalculator.calculateProbabilityDistributionDurations(durations))
        );

        BigDecimal weightedDeviation = new BigDecimal(
                String.valueOf(PERTCalculator.calculateProbabilityDistributionStandardDeviations(deviations))
        );

        printer.printTaskSequenceDuration(weightedDuration);
        printer.printTaskSequenceStandardDeviation(weightedDeviation);

        printer.printFinalBatchEstimate(
                new BigDecimal(String.valueOf(weightedDuration.add(weightedDeviation)))
        );

        printer.printFinalBatchEstimateWorstCase(
                new BigDecimal(
                        String.valueOf(weightedDuration.add(weightedDeviation.multiply(new BigDecimal("2"))))
                )
        );
    }
}
