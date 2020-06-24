package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal MEAL1 = new Meal(100002, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(100003, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(100004, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(100005, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL5 = new Meal(100006, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL6 = new Meal(100007, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL7 = new Meal(100008, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    public static final Meal ADMIN_MEAL = new Meal(100009, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Admin Завтрак", 500);
    public static final Meal MEAL11 = new Meal(100010, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.JUNE, 24, 10, 0), "NewDescription", 1555);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }

}
