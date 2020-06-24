package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    @Qualifier("jdbc meal repo")
    private MealRepository repository;

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(meal, MEAL1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ID, USER_ID);
        assertNull(repository.get(MEAL_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, MEAL_ID));
    }

    @Test
    public void getBetweenInclusive() {
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(MEAL_ID, USER_ID), updated);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void getNotFoundByID() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void getNotFoundByAnotherUser() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID, ADMIN_ID));
    }

    @Test
    public void duplicateMealDateTimeCreate() throws Exception {
        Meal meal = new Meal(MEAL1);
        meal.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0));
        assertThrows(DuplicateKeyException.class, () ->
                service.update(meal, USER_ID));
    }
}