package ru.javawebinar.topjava.util.datetimeutilities;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class LocalDateTimeInRestAnnotationFormatterFactory implements AnnotationFormatterFactory<LocalDateTimeFromRest> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<>(Collections.singletonList(String.class));
    }

    @Override
    public Printer<?> getPrinter(LocalDateTimeFromRest annotation, Class<?> aClass) {
        return new LocalDateTimeInRestFormatter();
    }

    @Override
    public Parser<?> getParser(LocalDateTimeFromRest annotation, Class<?> aClass) {
        return new LocalDateTimeInRestFormatter();
    }
}