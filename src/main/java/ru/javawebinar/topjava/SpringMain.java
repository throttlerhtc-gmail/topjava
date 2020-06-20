package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            MealRestController mealController = appCtx.getBean(MealRestController.class);
//            mealController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, SecurityUtil.authUserId()));
//            mealController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, SecurityUtil.authUserId()));
//            mealController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, SecurityUtil.authUserId()));
//            mealController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, SecurityUtil.authUserId()));
//            mealController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, SecurityUtil.authUserId()));
//            mealController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, SecurityUtil.authUserId()));
//            mealController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, SecurityUtil.authUserId()));
            Meal m = new Meal(1, LocalDateTime.now(), "night", 120, 1);
            System.out.println("++" + m.getId());
            System.out.println(m);
            mealController.getAll().forEach(System.out::println);
            System.out.println(mealController.get(2));
            mealController.delete(2);
            mealController.getAll().forEach(System.out::println);
            System.out.println("-------------------");
            mealController.update(m, 1);
            mealController.getAll().forEach(System.out::println);
        }
    }
}
