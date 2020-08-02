package ru.javawebinar.topjava.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.model.Employee;
import ru.javawebinar.topjava.web.user.AbstractUserController;

@RestController
public class StringToEmployeeConverterController extends AbstractUserController {

    @GetMapping("/string-to-employee")
    public ResponseEntity<Employee> getStringToEmployee(
            @RequestParam("employee") Employee employee) {
        return ResponseEntity.ok(employee);
    }

}
