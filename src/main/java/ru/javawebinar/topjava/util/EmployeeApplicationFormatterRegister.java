package ru.javawebinar.topjava.util;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

public class EmployeeApplicationFormatterRegister implements FormatterRegistrar {

    @Override
    public void registerFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addConverter(new StringToEmployeeConverter());
        formatterRegistry.addFormatter(new StringToEmployeeFormatter());
    }
}
