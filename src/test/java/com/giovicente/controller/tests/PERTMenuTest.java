package com.giovicente.controller.tests;

import com.giovicente.controller.PERTMenu;
import com.giovicente.utils.PrinterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
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

    @Mock
    private PrinterImpl printer;

    @Test
    void shouldThrowIllegalStateExceptionWhenAttemptingToInstantiate() throws NoSuchMethodException {
        Constructor<PERTMenu> constructor = PERTMenu.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);

        assertTrue(exception.getCause() instanceof IllegalStateException);
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
                .thenReturn(String.valueOf('A'))
                .thenReturn(String.valueOf('Y'));

        PERTMenu.hasContinuity(mockScanner);

        System.setOut(System.out);

        String actualPrintedOutput = outputStream.toString();
        String expectedPrintedOutput
                = "Do you want to do another estimate? Type Y or N: " +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \r\n" +
                "Invalid Option! It must be Y or N!!!\r\n" +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \r\n" +
                "Do you want to do another estimate? Type Y or N: " +
                "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * \r\n";

        Assertions.assertEquals(expectedPrintedOutput, actualPrintedOutput);
    }
}
