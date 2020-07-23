package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


@Controller
public class JspMealController {

    @Autowired
    private MealService service;

//    private MealRestController mealController;

/*
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        mealController = springContext.getBean(MealRestController.class);
    }
*/

/*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.isEmpty(request.getParameter("id"))) {
            mealController.create(meal);
        } else {
            mealController.update(meal, getId(request));
        }
        response.sendRedirect("meals");
    }
*/

/*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> delete(request, response);
            case "create", "update" -> create(request, response, action);
            case "filter" -> {
                LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
                LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
                LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
                LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
                request.setAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
            }
            default -> getAll(request, response);
        }
    }
*/

    @DeleteMapping("/meals")
    private String delete(@RequestParam(name="id", required=true, defaultValue="  ") Integer id, HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        service.delete(id, userId);
        return "redirect:meals";
    }

    @GetMapping("/meals")
    private String getAll(Model model, HttpServletRequest request) {
        int userId = SecurityUtil.authUserId();
        model.addAttribute("meals", service.getAll(userId));
        return "meals";
    }

    @PostMapping("/meals")
    public String create(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) ;
        request.setAttribute("meal", meal);
        return "redirect:mealForm";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
