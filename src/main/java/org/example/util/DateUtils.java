package org.example.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {


    private String defaultFormatter;

    public DateUtils(String defaultFormatter) {
        this.defaultFormatter = defaultFormatter;
    }

    public DateTimeFormatter getFormatter(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(defaultFormatter);
        return formatter;
    }

    public String formatDate(LocalDate date) {

        return date.format(getFormatter()) ;
    }
}
