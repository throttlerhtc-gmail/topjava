package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.model.Employee;

import java.text.ParseException;
import java.util.Locale;

public class StringToEmployeeFormatter implements Formatter<Employee> {
    @Override
    public Employee parse(String text, Locale locale) throws ParseException {
        return new Employee(1, 2000);
    }

    @Override
    public String print(Employee object, Locale locale) {
        return "Employee{" +
                "id: 1" +
                "salary: 2000" +
                "}";
    }
}
