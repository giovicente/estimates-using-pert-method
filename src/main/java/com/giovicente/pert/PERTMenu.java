package com.giovicente.pert;

import com.giovicente.utils.Printer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.giovicente.pert.PERTEstimateRetriever.*;

public class PERTMenu {

    private PERTMenu() {
        throw new IllegalStateException("This class can't be instantiated");
    }

    public static void runPERT() {
        Printer.printAsterisks();
        Printer.printTitle();
        Printer.printAsterisks();

        Scanner pertScanner = new Scanner(System.in);

        final int INDIVIDUAL_ESTIMATE = 1;
        final int BATCH_ESTIMATE = 2;

        BigDecimal optimistic;
        BigDecimal nominal;
        BigDecimal pessimistic;
        BigDecimal realistic;

        boolean isRunning = false;

        do {
            Printer.printOptionMessage();
            int option = pertScanner.nextInt();
            Printer.printAsterisks();

            while (option != INDIVIDUAL_ESTIMATE && option != BATCH_ESTIMATE) {
                Printer.printInvalidOptionMessage();
                Printer.printAsterisks();

                Printer.printOptionMessage();
                option = pertScanner.nextInt();
                Printer.printAsterisks();
            }

            if (option == INDIVIDUAL_ESTIMATE) {
                optimistic = getOptimistic(pertScanner);
                nominal = getNominal(pertScanner);
                pessimistic = getPessimistic(pertScanner);

                realistic = getIndividualEstimate(optimistic, nominal, pessimistic);
                Printer.printRealisticEstimate(realistic);
                Printer.printAsterisks();
            } else {
                List<BigDecimal> durations = new ArrayList<>();
                List<BigDecimal> deviations = new ArrayList<>();

                getBatchEstimate(pertScanner, durations, deviations);
                Printer.printAsterisks();
            }

            char continuity = hasContinuity(pertScanner);
            isRunning = (continuity == 'Y');
        } while (isRunning);

        pertScanner.close();
    }

    private static char hasContinuity(Scanner pertScanner) {
        Printer.printContinuityMessage();
        char continuity = Character.toUpperCase(pertScanner.next().charAt(0));
        Printer.printAsterisks();

        while (continuity != 'Y' && continuity != 'N') {
            Printer.printInvalidContinuityMessage();
            Printer.printAsterisks();

            Printer.printContinuityMessage();
            continuity = Character.toUpperCase(pertScanner.next().charAt(0));
            Printer.printAsterisks();
        }

        return continuity;
    }

    private static void getBatchEstimate(Scanner pertscanner, List<BigDecimal> durations, List<BigDecimal> deviations) {
        BigDecimal optimistic;
        BigDecimal pessimistic;
        boolean hasMoreEstimates;
        BigDecimal nominal;

        do {
            optimistic = getOptimistic(pertscanner);
            nominal = getNominal(pertscanner);
            pessimistic = getPessimistic(pertscanner);
            Printer.printAsterisks();

            BigDecimal duration = getDuration(optimistic, nominal, pessimistic);

            durations.add(duration);
            deviations.add(getIndividualStandardDeviation(optimistic, pessimistic));
            Printer.printAsterisks();

            char continuity = hasContinuity(pertscanner);
            hasMoreEstimates = (continuity == 'Y');
        } while (hasMoreEstimates);

        BigDecimal weightedDuration = new BigDecimal(
                String.valueOf(PERTCalculator.calculateProbabilityDistributionDurations(durations))
        );

        BigDecimal weightedDeviation = new BigDecimal(
                String.valueOf(PERTCalculator.calculateProbabilityDistributionStandardDeviations(deviations))
        );

        Printer.printTaskSequenceDuration(weightedDuration);
        Printer.printTaskSequenceStandardDeviation(weightedDeviation);

        Printer.printFinalBatchEstimate(
                new BigDecimal(String.valueOf(weightedDuration.add(weightedDeviation)))
        );

        Printer.printFinalBatchEstimateWorstCase(
                new BigDecimal(
                        String.valueOf(weightedDuration.add(weightedDeviation.multiply(new BigDecimal("2"))))
                )
        );
    }
}
