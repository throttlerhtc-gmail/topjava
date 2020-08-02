package ru.javawebinar.topjava.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.service.MealService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class StringToEmployeeConverterControllerTest extends AbstractControllerTest {

    @Autowired
    MealService service;

    @Test
    void getStringToEmployee() throws Exception {
        perform(MockMvcRequestBuilders.get("/string-to-employee?employee=1,2000"))
                .andDo(print());
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.salary", is(2000.0))) {
    }
}
