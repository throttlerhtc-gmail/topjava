package ru.javawebinar.topjava.util.datetimeutilities;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;

public final class LocalDateTimeInRestFormatter implements Formatter<LocalDateTime> {

    //    private final String pattern;
//
//    public LocalDateTimeInRestFormatter(String pattern) {
//        this.pattern = pattern;
//    }


    public LocalDateTimeInRestFormatter() {
    }

    public String print(LocalDateTime localDateTime, Locale locale) {
//        return localDateTime.toString();
        return LocalDateTime.of(1, 1, 1, 0, 0).toString();
    }
//    public String print(LocalDateTime localDateTime, Locale locale) {
//        return DateTimeUtil.toString(localDateTime);
//    }

    public LocalDateTime parse(String formatted, Locale locale) throws ParseException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return LocalDateTime.of(1, 1, 1, 0, 0);
//        return LocalDateTime.of(DateTimeUtil.getDateOrMinDate(formatted), DateTimeUtil.getTimeOrMinTime(formatted));
    }
}