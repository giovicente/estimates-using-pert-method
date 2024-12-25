package com.giovicente.controller.tests;

import com.giovicente.controller.PERTMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PERTMenuTest {

    @Mock
    private Scanner mockScanner;

    private String simulatedInputs;

    @Test
    void shouldThrowIllegalStateExceptionWhenAttemptingToInstantiate() throws NoSuchMethodException {
        Constructor<PERTMenu> constructor = PERTMenu.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);

        assertInstanceOf(IllegalStateException.class, exception.getCause());
        assertEquals("This class can't be instantiated", exception.getCause().getMessage());
    }

    @Test
    void shouldHaveContinuity() {
        when(mockScanner.next()).thenReturn(String.valueOf('Y'));
        Assertions.assertEquals('Y', PERTMenu.hasContinuity(mockScanner));
    }

    @Test
    void shouldNotHaveContinuity() {
        when(mockScanner.next()).thenReturn(String.valueOf('N'));
        Assertions.assertEquals('N', PERTMenu.hasContinuity(mockScanner));
    }

    @Test
    void shouldReturnInvalidContinuityMessage() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        when(mockScanner.next())
                .thenReturn(String.valueOf('A')) // Invalid input continuity value
                .thenReturn(String.valueOf('Y')); // Valid input continuity value

        PERTMenu.hasContinuity(mockScanner);

        System.setOut(System.out);

        String actualPrintedOutput = outputStream.toString();

        assertTrue(actualPrintedOutput.contains("Do you want to do another estimate? Type Y or N: "));
        assertTrue(actualPrintedOutput.contains("Invalid Option! It must be Y or N!!!"));
    }

    @Test
    void shouldReturnInvalidOptionMessageForIndividualEstimate() {
        /*
        * Simulated inputs for runPERT() method:
        * . 0 is an invalid option for selecting either an individual (1) or batch (2) estimate
        * . 1 is a valid option for selecting an individual estimate
        * . 3 is the optimistic estimate
        * . 5 is the nominal estimate
        * . 11 is the pessimistic estimate
        * . N indicates that the user does not want to make a new estimate
        * */
        simulatedInputs = "0\n1\n3\n5\n11\nN";
        InputStream inputStream = new ByteArrayInputStream(simulatedInputs.getBytes());

        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream actualOutput = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            PERTMenu.runPERT();
        } finally {
            System.setOut(actualOutput);
            System.setIn(System.in);
        }

        String capturedOutput = outputStream.toString();

        assertTrue(capturedOutput.contains("Invalid Option! It must be 1 or 2!!!"));
    }

    @Test
    void shouldReturnInvalidOptionMessageForBatchEstimate() {
        /*
         * Simulated inputs for runPERT() method:
         * . 2 is a valid option for selecting a batch estimate
         * . 1 is the optimistic estimate
         * . 3 is the nominal estimate
         * . 5 is the pessimistic estimate
         * . A is an invalid option for the question about add a new estimate to the list
         * . First N indicates that the user does not want to add a new estimate to the list
         * . B is an invalid option for the question about whether to make a new estimate
         * . Second N indicates that the user does not want to make a new estimate
         * */
        simulatedInputs = "2\n1\n3\n5\nA\nN\nB\nN";
        InputStream inputStream = new ByteArrayInputStream(simulatedInputs.getBytes());

        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream actualOutput = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            PERTMenu.runPERT();
        } finally {
            System.setOut(actualOutput);
            System.setIn(System.in);
        }

        String capturedOutput = outputStream.toString();

        assertTrue(capturedOutput.contains("Invalid Option! It must be Y or N!!!"));
    }

    @Test
    void shouldReturnCorrectFinalEstimations() {
        /*
         * Simulated inputs for runPERT() method:
         * . 2 is a valid option for selecting a batch estimate
         * . 1 is the optimistic estimate
         * . 3 is the nominal estimate
         * . 5 is the pessimistic estimate
         * . First N indicates that the user does not want to add a new estimate to the list
         * . Second N indicates that the user does not want to make a new estimate
         * */
        simulatedInputs = "2\n1\n3\n5\nN\nN";
        InputStream inputStream = new ByteArrayInputStream(simulatedInputs.getBytes());

        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream actualOutput = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            PERTMenu.runPERT();
        } finally {
            System.setOut(actualOutput);
            System.setIn(System.in);
        }

        String capturedOutput = outputStream.toString();

        assertTrue(capturedOutput.contains("Task Sequence Duration: 3.0"));
        assertTrue(capturedOutput.contains("Task Sequence Standard Deviation: 0.7"));
        assertTrue(capturedOutput.contains("Final Estimate: 3.7"));
        assertTrue(capturedOutput.contains("Final Estimate (Worst Case Scenario): 4.4"));
    }
}
