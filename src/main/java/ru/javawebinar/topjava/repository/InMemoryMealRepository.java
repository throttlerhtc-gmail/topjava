package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface InMemoryMealRepository {
    public boolean create(Meal meal); //  create meal from UI in repo

    public boolean update(Meal meal, int mealId); // update meal with Id in repo

    public boolean delete(int mealId);   // delete from repo

    public Meal getMealById(int mealId); // get meal by Id

    public List<Meal> getMeals(); // get all meal for all users
}
