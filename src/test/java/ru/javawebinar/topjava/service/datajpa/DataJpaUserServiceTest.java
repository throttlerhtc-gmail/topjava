package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    @Transactional
    public void getWithMeals() {
        User actual = service.get(USER_ID);
        USER_MATCHER.assertMatch(actual, USER_MEAL);
    }

}