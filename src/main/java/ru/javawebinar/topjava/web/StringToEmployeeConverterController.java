package ru.javawebinar.topjava.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.model.Employee;
import ru.javawebinar.topjava.web.user.AbstractUserController;

@RestController
public class StringToEmployeeConverterController extends AbstractUserController {

    @GetMapping(value = "/string-to-employee")
    public ResponseEntity<Object> getStringToEmployee(@RequestParam("employee") Employee employee) {
        System.out.println(employee + "\n++++++++++++++++++++++++++++++");
        return ResponseEntity.ok(employee);
    }

}
