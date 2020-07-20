package ru.javawebinar.topjava.service.datajpa;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaMealServiceTest extends MealServiceTest {


    @Autowired
    private MealService service;

    @Test
    @Transactional
    public void getWithUser() {
        Meal actual = service.get(MEAL1_ID, USER_ID);
        MEAL_MATCHER.assertAllFieldsMatch(actual, MEAL1_USER);
    }
}
