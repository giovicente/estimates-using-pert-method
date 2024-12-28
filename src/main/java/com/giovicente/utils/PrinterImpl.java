package com.giovicente.utils;

import com.giovicente.processor.Printer;

import java.math.BigDecimal;

public class PrinterImpl implements Printer {

    public void printTitle() {
        System.out.println(
                "______ ___________ _____   _____ _____ _____ ________  ___  ___ _____ _____ _____ \n" +
                        "| ___ \\  ___| ___ \\_   _| |  ___/  ___|_   _|_   _|  \\/  | / _ \\_   _|  ___/  ___|\n" +
                        "| |_/ / |__ | |_/ / | |   | |__ \\ `--.  | |   | | | .  . |/ /_\\ \\| | | |__ \\ `--. \n" +
                        "|  __/|  __||    /  | |   |  __| `--. \\ | |   | | | |\\/| ||  _  || | |  __| `--. \\\n" +
                        "| |   | |___| |\\ \\  | |   | |___/\\__/ / | |  _| |_| |  | || | | || | | |___/\\__/ /\n" +
                        "\\_|   \\____/\\_| \\_| \\_/   \\____/\\____/  \\_/  \\___/\\_|  |_/\\_| |_/\\_/ \\____/\\____/ \n"
        );
    }

    public void printOptionMessage() {
        System.out.print("Choose your Option - 1 -> Individual or 2 -> Batch: ");
    }

    public void printInvalidOptionMessage() {
        System.out.println("Invalid Option! It must be 1 or 2!!!");
    }

    public void printInsertOptimisticEstimate() {
        System.out.print("Insert your optimistic estimate in days: ");
    }

    public void printInsertNominalEstimate() {
        System.out.print("Insert your nominal estimate in days: ");
    }

    public void printInsertPessimisticEstimate() {
        System.out.print("Insert your pessimistic estimate in days: ");
    }

    public void printDuration(BigDecimal duration) {
        System.out.println("Duration = " + duration);
    }

    public void printStandardDeviation(BigDecimal standardDeviation) {
        System.out.println("Standard Deviation = " + standardDeviation);
    }

    public void printRealisticEstimate(BigDecimal realistic) {
        System.out.println("Estimate = " + realistic);
    }

    public void printContinuityMessage() {
        System.out.print("Do you want to do another estimate? Type Y or N: ");
    }

    public void printInvalidContinuityMessage() {
        System.out.println("Invalid Option! It must be Y or N!!!");
    }

    public void printTaskSequenceDuration(BigDecimal weightedDuration) {
        System.out.println("Task Sequence Duration: " + weightedDuration);
    }

    public void printTaskSequenceStandardDeviation(BigDecimal weightedStandardDeviation) {
        System.out.println("Task Sequence Standard Deviation: " + weightedStandardDeviation);
    }

    public void printFinalBatchEstimate(BigDecimal weightedEstimate) {
        System.out.println("Final Estimate: " + weightedEstimate);
    }

    public void printFinalBatchEstimateWorstCase(BigDecimal weightedEstimate) {
        System.out.println("Final Estimate (Worst Case Scenario): " + weightedEstimate);
    }

    public void jumpLine() {
        System.out.println();
    }

    public void printAsterisks() {
        final int NUMBER_OF_ASTERISKS = 50;

        for (int i = 0; i < NUMBER_OF_ASTERISKS; i++) {
            System.out.print("*" + " ");
        }

        jumpLine();
    }

    @Override
    public void printInvalidInputValue() {
        System.out.println("Invalid Input! The value must be a positive numeric with a dot as the decimal separator.");
    }
}
