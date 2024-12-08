package com.giovicente.processor;

import java.math.BigDecimal;
import java.math.MathContext;

public interface BigDecimalSquareRoot {
    BigDecimal sqrt(BigDecimal value, MathContext precisionContext);
}
