package com.giovicente.utils;

import java.math.BigDecimal;

public class Printer {

    private Printer() {
        throw new IllegalStateException("This class can't be instantiated");
    }

    public static void printTitle() {
        System.out.println(
                "______ ___________ _____   _____ _____ _____ ________  ___  ___ _____ _____ _____ \n" +
                "| ___ \\  ___| ___ \\_   _| |  ___/  ___|_   _|_   _|  \\/  | / _ \\_   _|  ___/  ___|\n" +
                "| |_/ / |__ | |_/ / | |   | |__ \\ `--.  | |   | | | .  . |/ /_\\ \\| | | |__ \\ `--. \n" +
                "|  __/|  __||    /  | |   |  __| `--. \\ | |   | | | |\\/| ||  _  || | |  __| `--. \\\n" +
                "| |   | |___| |\\ \\  | |   | |___/\\__/ / | |  _| |_| |  | || | | || | | |___/\\__/ /\n" +
                "\\_|   \\____/\\_| \\_| \\_/   \\____/\\____/  \\_/  \\___/\\_|  |_/\\_| |_/\\_/ \\____/\\____/ \n"
        );
    }

    public static void printOptionMessage() {
        System.out.print("Choose your Option - 1 -> Individual or 2 -> Batch: ");
    }

    public static void printInvalidOptionMessage() {
        System.out.println("Invalid Option! It must be 1 or 2!!!");
    }

    public static void printInsertOptimisticEstimate() {
        System.out.print("Insert your optimistic estimate in days: ");
    }

    public static void printInsertNominalEstimate() {
        System.out.print("Insert your nominal estimate in days: ");
    }
    public static void printInsertPessimisticEstimate() {
        System.out.print("Insert your pessimistic estimate in days: ");
    }

    public static void printDuration(BigDecimal duration) {
        System.out.println("Duration = " + duration);
    }

    public static void printStandardDeviation(BigDecimal standardDeviation) {
        System.out.println("Standard Deviation = " + standardDeviation);
    }

    public static void printRealisticEstimate(BigDecimal realistic) {
        System.out.println("Estimate = " + realistic);
    }

    public static void printContinuityMessage() {
        System.out.print("Do you want to do another estimate? Type Y or N: ");
    }

    public static void printInvalidContinuityMessage() {
        System.out.println("Invalid Option! It must be Y or N!!!");
    }

    public static void printTaskSequenceDuration(BigDecimal weightedDuration) {
        System.out.println("Task Sequence Duration: " + weightedDuration);
    }

    public static void printTaskSequenceStandardDeviation(BigDecimal weightedStandardDeviation) {
        System.out.println("Task Sequence Standard Deviation: " + weightedStandardDeviation);
    }

    public static void printFinalBatchEstimate(BigDecimal weightedEstimate) {
        System.out.println("Final Estimate: " + weightedEstimate);
    }

    public static void printFinalBatchEstimateWorstCase(BigDecimal weightedEstimate) {
        System.out.println("Final Estimate (Worst Case Scenario): " + weightedEstimate);
    }

    public static void jumpLine() {
        System.out.println();
    }

    public static void printAsterisks() {
        final int NUMBER_OF_ASTERISKS = 42;

        for (int i = 0; i < NUMBER_OF_ASTERISKS; i++) {
            System.out.print("*" + " ");
        }

        jumpLine();
    }
}
