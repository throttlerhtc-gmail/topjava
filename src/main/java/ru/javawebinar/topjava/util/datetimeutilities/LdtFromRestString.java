package ru.javawebinar.topjava.util.datetimeutilities;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public final class LdtFromRestString implements Converter<String, LocalDateTime> {

    public LdtFromRestString() {
    }

    @Override
    public LocalDateTime convert(String source) {
//        return LocalDateTime.of(DateTimeUtil.getDateOrMinDate(source), DateTimeUtil.getTimeOrMinTime(source));
        return LocalDateTime.of(1, 1, 1, 0, 0);
    }
}
