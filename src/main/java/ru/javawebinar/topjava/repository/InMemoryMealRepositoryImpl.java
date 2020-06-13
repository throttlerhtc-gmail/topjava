package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryMealRepositoryImpl implements InMemoryMealRepository {

    private final List<Meal> meals = Collections.synchronizedList(new ArrayList<>());

    @Override
    public boolean create(Meal meal) {
        boolean res = false;
        try {
            if (!meals.contains(meal)) {
                res = meals.add(meal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean update(Meal meal, int mealId) {
        boolean res = false;
        try {
            if (!meals.contains(meal)) {
                return false;
            } else {
                int id = meals.indexOf(meal);
                res = meals.add(meal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean delete(int mealId) {
        boolean res = false;
        try {
            if (meals.remove(mealId) != null) res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }

    @Override
    public Meal getMealById(int mealId) {
        return meals.get(mealId);
    }

    @Override
    public List<Meal> getMeals() {
        return meals;
    }
}
