package org.example.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberUtils {

    private String numberDefaultFormat;

    public NumberUtils(String numberDefaultFormat) {
        this.numberDefaultFormat = numberDefaultFormat;
    }

    public String formatNumber(BigDecimal number) {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.of("pt", "BR"));

        DecimalFormat df = new DecimalFormat(numberDefaultFormat, symbols);

        return df.format(number);
    }
}
