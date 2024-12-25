package com.giovicente.processor;

import java.math.BigDecimal;

public interface Printer {
    void printTitle();
    void printOptionMessage();
    void printInvalidOptionMessage();
    void printInsertOptimisticEstimate();
    void printInsertNominalEstimate();
    void printInsertPessimisticEstimate();
    void printDuration(BigDecimal duration);
    void printStandardDeviation(BigDecimal standardDeviation);
    void printRealisticEstimate(BigDecimal realistic);
    void printContinuityMessage();
    void printInvalidContinuityMessage();
    void printTaskSequenceDuration(BigDecimal weightedDuration);
    void printTaskSequenceStandardDeviation(BigDecimal weightedStandardDeviation);
    void printFinalBatchEstimate(BigDecimal weightedEstimate);
    void printFinalBatchEstimateWorstCase(BigDecimal weightedEstimate);
    void jumpLine();
    void printAsterisks();
    void printInvalidInputValue();
}
