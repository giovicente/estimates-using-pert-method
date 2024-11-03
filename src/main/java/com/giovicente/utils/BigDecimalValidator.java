package com.giovicente.utils;

import java.math.BigDecimal;

public class BigDecimalValidator {

    private BigDecimalValidator() {
        throw new IllegalStateException("This class can't be instantiated");
    }

    public static boolean isPositiveNumeric(BigDecimal input) {
        if (input.scale() < 0 || input.compareTo(BigDecimal.ZERO) < 0) {
            throw new NumberFormatException("Your input must be a valid positive numeric value.");
        }

        return true;
    }
}
