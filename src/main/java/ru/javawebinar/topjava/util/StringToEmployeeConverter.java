package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Employee;

@Component
public class StringToEmployeeConverter implements Converter<String, Employee> {

    @Override
    public Employee convert(String from) {
        return new Employee(1, 2000);
    }

}
