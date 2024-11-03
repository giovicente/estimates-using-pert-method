package com.giovicente.utils;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalSquareRoot {

    private BigDecimalSquareRoot() {
        throw new IllegalStateException("This class can't be instantiated");
    }

    public static BigDecimal sqrt(BigDecimal value, MathContext precisionContext) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArithmeticException("Negative value: " + value);
        }

        if (value.equals(BigDecimal.ZERO) || value.equals(BigDecimal.ONE)) {
            return value; // The square root of 0 or 1 is equal to the number itself.
        }

        BigDecimal initialGuess = value.divide(BigDecimal.valueOf(2), precisionContext);
        BigDecimal refinedGuess;

        do {
            refinedGuess = initialGuess.add(value.divide(initialGuess, precisionContext))
                    .divide(BigDecimal.valueOf(2), precisionContext); // Newton-Raphson method
            if (refinedGuess.compareTo(initialGuess) == 0) {
                break; // Converged
            }
            initialGuess = refinedGuess;
        } while (true);

        return refinedGuess;
    }
}
