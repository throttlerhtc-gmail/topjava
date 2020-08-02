package ru.javawebinar.topjava.util.datetimeutilities;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.time.LocalDateTime;

public final class LdtFromRestConverterFactory implements ConverterFactory<String, LocalDateTime> {
    @Override
    public <T extends LocalDateTime> Converter<String, T> getConverter(Class<T> targetType) {
        return null;
    }
}
