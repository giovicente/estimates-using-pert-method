package com.giovicente.pert;

import com.giovicente.utils.BigDecimalValidator;
import com.giovicente.utils.Printer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class PERTMenu {

    public static void runPERT() {
        Printer.printAsterisks();
        Printer.printTitle();
        Printer.printAsterisks();

        Scanner PERTScanner = new Scanner(System.in);

        final int INDIVIDUAL_ESTIMATE = 1;
        final int BATCH_ESTIMATE = 2;

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
                calculateIndividualEstimate(PERTScanner);

            } else {
                System.out.println("Batch Estimates coming soon. . .");
                Printer.printAsterisks();
            }

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

            isRunning = (continuity == 'Y');

        } while (isRunning);

        PERTScanner.close();
    }

    private static void calculateIndividualEstimate(Scanner PERTScanner) {
        Printer.printInsertOptimisticEstimate();
        BigDecimal optimistic = new BigDecimal(String.valueOf(PERTScanner.nextBigDecimal()));

        Printer.printInsertNominalEstimate();
        BigDecimal nominal = new BigDecimal(String.valueOf(PERTScanner.nextBigDecimal()));

        Printer.printInsertPessimisticEstimate();
        BigDecimal pessimistic = new BigDecimal(String.valueOf(PERTScanner.nextBigDecimal()));

        Printer.printAsterisks();

        BigDecimal duration;
        Printer.printDuration(duration = new BigDecimal(
                String.valueOf(PERTCalculator.calculateDuration(optimistic, nominal, pessimistic)))
        );

        BigDecimal standardDeviation;
        Printer.printStandardDeviation(standardDeviation = new BigDecimal(
                String.valueOf(PERTCalculator.calculateStandardDeviation(pessimistic, optimistic))
        ));

        Printer.printRealisticEstimate(duration.add(standardDeviation));

        Printer.printAsterisks();
    }
}
