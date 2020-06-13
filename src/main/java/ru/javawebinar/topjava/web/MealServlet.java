package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(ru.javawebinar.topjava.web.MealServlet.class);

    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private InMemoryMealRepositoryImpl repository;

    public MealServlet() {
        super();
        repository = new InMemoryMealRepositoryImpl();
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
//        List<MealTo> meals = MealsUtil.filteredByStreams(MealsUtil.getHardcodedMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
//        request.setAttribute("meals", meals);
//        request.getRequestDispatcher("/meals.jsp").forward(request, response);
//        response.sendRedirect("meals.jsp");
        String forward="";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            repository.delete(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", repository.getMeals());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = repository.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeals")){
            forward = LIST_MEAL;
            request.setAttribute("meals", repository.getMeals());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDateTime dateTime = null;
        int calories = 0;
        try {
            dateTime = LocalDateTime.parse(request.getParameter("dateTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            calories = Integer.parseInt(request.getParameter("calories"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String description = request.getParameter("description");
        Meal meal = new Meal(dateTime, description, calories);
        String mealId = request.getParameter("mealId");
        int id = 0;
        if(mealId == null || mealId.isEmpty())
        {
            repository.create(meal);
        }
        else
        {
            try {
                id = Integer.parseInt(mealId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            repository.update(meal, id);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("meals", repository.getMeals());
        view.forward(request, response);
    }
}
