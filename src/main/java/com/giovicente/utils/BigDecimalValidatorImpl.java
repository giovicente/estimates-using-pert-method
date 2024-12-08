package com.giovicente.utils;

import com.giovicente.processor.BigDecimalValidator;

import java.math.BigDecimal;

public class BigDecimalValidatorImpl implements BigDecimalValidator {

    public boolean isPositiveNumeric(BigDecimal input) {
        if (input.scale() < 0 || input.compareTo(BigDecimal.ZERO) < 0) {
            throw new NumberFormatException("Your input must be a valid positive numeric value.");
        }

        return true;
    }
}
