package com.giovicente.pert;

import com.giovicente.utils.Printer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.giovicente.pert.PERTEstimateRetriever.*;

public class PERTMenu {

    public static void runPERT() {
        Printer.printAsterisks();
        Printer.printTitle();
        Printer.printAsterisks();

        Scanner PERTScanner = new Scanner(System.in);

        final int INDIVIDUAL_ESTIMATE = 1;
        final int BATCH_ESTIMATE = 2;

        BigDecimal optimistic, nominal, pessimistic, realistic;

        boolean isRunning = false;

        do {
            Printer.printOptionMessage();
            int option = PERTScanner.nextInt();
            Printer.printAsterisks();

            while (option != INDIVIDUAL_ESTIMATE && option != BATCH_ESTIMATE) {
                Printer.printInvalidOptionMessage();
                Printer.printAsterisks();

                Printer.printOptionMessage();
                option = PERTScanner.nextInt();

                Printer.printAsterisks();
            }

            if (option == INDIVIDUAL_ESTIMATE) {
                optimistic = getOptimistic(PERTScanner);
                nominal = getNominal(PERTScanner);
                pessimistic = getPessimistic(PERTScanner);

                realistic = getIndividualEstimate(optimistic, nominal, pessimistic);
                Printer.printRealisticEstimate(realistic);
                Printer.printAsterisks();
            } else {
                boolean hasMoreEstimates;
                List<BigDecimal> durations = new ArrayList<>();
                List<BigDecimal> deviations = new ArrayList<>();
                BigDecimal duration;

                do {
                    optimistic = getOptimistic(PERTScanner);
                    nominal = getNominal(PERTScanner);
                    pessimistic = getPessimistic(PERTScanner);
                    Printer.printAsterisks();

                    duration = getDuration(optimistic, nominal, pessimistic);

                    durations.add(duration);
                    deviations.add(getIndividualStandardDeviation(optimistic, pessimistic));
                    Printer.printAsterisks();

                    char continuity = hasContinuity(PERTScanner);
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
                Printer.printAsterisks();
            }

            char continuity = hasContinuity(PERTScanner);
            isRunning = (continuity == 'Y');

        } while (isRunning);

        PERTScanner.close();
    }

    private static char hasContinuity(Scanner PERTScanner) {
        Printer.printContinuityMessage();
        char continuity = Character.toUpperCase(PERTScanner.next().charAt(0));
        Printer.printAsterisks();

        while (continuity != 'Y' && continuity != 'N') {
            Printer.printInvalidContinuityMessage();
            Printer.printAsterisks();

            Printer.printContinuityMessage();
            continuity = Character.toUpperCase(PERTScanner.next().charAt(0));

            Printer.printAsterisks();
        }
        return continuity;
    }
}
