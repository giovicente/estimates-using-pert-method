package com.giovicente.adapter.tests;

import com.giovicente.adapter.PERTEstimateRetriever;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PERTEstimateRetrieverTest {

    @Mock
    private Scanner mockScanner;

    @Test
    void shouldThrowIllegalStateExceptionWhenAttemptingToInstantiate() throws NoSuchMethodException {
        Constructor<PERTEstimateRetriever> constructor = PERTEstimateRetriever.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);

        assertTrue(exception.getCause() instanceof IllegalStateException);
        assertEquals("This class can't be instantiated", exception.getCause().getMessage());
    }

    @Test
    void shouldReturnCorrectOptimisticInput() {
        when(mockScanner.nextBigDecimal()).thenReturn(new BigDecimal("1.5"));
        BigDecimal result = PERTEstimateRetriever.getOptimistic(mockScanner);

        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    void shouldReturnIncorrectOptimisticInput() {
        when(mockScanner.nextBigDecimal()).thenReturn(new BigDecimal("1.5"));
        BigDecimal result = PERTEstimateRetriever.getOptimistic(mockScanner);

        Assertions.assertNotEquals(new BigDecimal("10.5"), result);
    }

    @Test
    void shouldThrowExceptionForNullInOptimisticEstimation() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PERTEstimateRetriever.getOptimistic(null);
        });

        assertEquals(
                "Cannot invoke \"java.util.Scanner.nextBigDecimal()\" because \"pertScanner\" is null",
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnCorrectNominalInput() {
        when(mockScanner.nextBigDecimal()).thenReturn(new BigDecimal("1.5"));
        BigDecimal result = PERTEstimateRetriever.getNominal(mockScanner);

        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    void shouldReturnIncorrectNominalInput() {
        when(mockScanner.nextBigDecimal()).thenReturn(new BigDecimal("1.5"));
        BigDecimal result = PERTEstimateRetriever.getNominal(mockScanner);

        Assertions.assertNotEquals(new BigDecimal("10.5"), result);
    }

    @Test
    void shouldThrowExceptionForNullInNominalEstimation() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PERTEstimateRetriever.getNominal(null);
        });

        assertEquals(
                "Cannot invoke \"java.util.Scanner.nextBigDecimal()\" because \"pertScanner\" is null",
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnCorrectPessimisticInput() {
        when(mockScanner.nextBigDecimal()).thenReturn(new BigDecimal("1.5"));
        BigDecimal result = PERTEstimateRetriever.getPessimistic(mockScanner);

        assertEquals(new BigDecimal("1.5"), result);
    }

    @Test
    void shouldReturnIncorrectPessimisticInput() {
        when(mockScanner.nextBigDecimal()).thenReturn(new BigDecimal("1.5"));
        BigDecimal result = PERTEstimateRetriever.getPessimistic(mockScanner);

        Assertions.assertNotEquals(new BigDecimal("10.5"), result);
    }

    @Test
    void shouldThrowExceptionForNullInPessimisticEstimation() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PERTEstimateRetriever.getPessimistic(null);
        });

        assertEquals(
                "Cannot invoke \"java.util.Scanner.nextBigDecimal()\" because \"pertScanner\" is null",
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnCorrectDuration() {
        BigDecimal optimistic = BigDecimal.ONE;
        BigDecimal nominal = new BigDecimal("3");
        BigDecimal pessimistic = new BigDecimal("12");

        BigDecimal result = new BigDecimal(String.valueOf(
                PERTEstimateRetriever.getDuration(optimistic, nominal, pessimistic)
        ));

        Assertions.assertEquals(new BigDecimal("4.2"), result);
    }

    @Test
    void shouldReturnIncorrectDuration() {
        BigDecimal optimistic = BigDecimal.ONE;
        BigDecimal nominal = new BigDecimal("3");
        BigDecimal pessimistic = new BigDecimal("12");

        BigDecimal result = new BigDecimal(String.valueOf(
                PERTEstimateRetriever.getDuration(optimistic, nominal, pessimistic)
        ));

        Assertions.assertNotEquals(new BigDecimal("1.2"), result);
    }

    @Test
    void shouldThrowExceptionForNullInDurationEstimation() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PERTEstimateRetriever.getDuration(null, null, null);
        });

        assertEquals(
                "Cannot invoke \"java.math.BigDecimal.scale()\" because \"input\" is null",
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnCorrectStandardDeviation() {
        BigDecimal optimistic = BigDecimal.ONE;
        BigDecimal pessimistic = new BigDecimal("12");

        BigDecimal result = new BigDecimal(String.valueOf(
                PERTEstimateRetriever.getIndividualStandardDeviation(optimistic, pessimistic)
        ));

        Assertions.assertEquals(new BigDecimal("1.8"), result);
    }

    @Test
    void shouldReturnIncorrectStandardDeviation() {
        BigDecimal optimistic = BigDecimal.ONE;
        BigDecimal pessimistic = new BigDecimal("12");

        BigDecimal result = new BigDecimal(String.valueOf(
                PERTEstimateRetriever.getIndividualStandardDeviation(optimistic, pessimistic)
        ));

        Assertions.assertNotEquals(new BigDecimal("2"), result);
    }

    @Test
    void shouldThrowExceptionForNullInStandardDeviationEstimation() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PERTEstimateRetriever.getIndividualStandardDeviation(null, null);
        });

        assertEquals(
                "Cannot invoke \"java.math.BigDecimal.scale()\" because \"input\" is null",
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnCorrectIndividualEstimate() {
        BigDecimal optimistic = BigDecimal.ONE;
        BigDecimal nominal = new BigDecimal("3");
        BigDecimal pessimistic = new BigDecimal("12");

        BigDecimal result = new BigDecimal(String.valueOf(
                PERTEstimateRetriever.getIndividualEstimate(optimistic, nominal, pessimistic)
        ));

        Assertions.assertEquals(new BigDecimal("6.0"), result);
    }

    @Test
    void shouldReturnIncorrectIndividualEstimate() {
        BigDecimal optimistic = BigDecimal.ONE;
        BigDecimal nominal = new BigDecimal("3");
        BigDecimal pessimistic = new BigDecimal("12");

        BigDecimal result = new BigDecimal(String.valueOf(
                PERTEstimateRetriever.getIndividualEstimate(optimistic, nominal, pessimistic)
        ));

        Assertions.assertNotEquals(new BigDecimal("5.0"), result);
    }

    @Test
    void shouldReturnIncorrectIndividualEstimateDueToTheMissingDecimalPlace() {
        BigDecimal optimistic = BigDecimal.ONE;
        BigDecimal nominal = new BigDecimal("3");
        BigDecimal pessimistic = new BigDecimal("12");

        BigDecimal result = new BigDecimal(String.valueOf(
                PERTEstimateRetriever.getIndividualEstimate(optimistic, nominal, pessimistic)
        ));

        Assertions.assertNotEquals(new BigDecimal("6"), result);
    }

    @Test
    void shouldThrowExceptionForNullInIndividualEstimation() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PERTEstimateRetriever.getIndividualEstimate(null, null, null);
        });

        assertEquals(
                "Cannot invoke \"java.math.BigDecimal.scale()\" because \"input\" is null",
                exception.getMessage()
        );
    }
}
