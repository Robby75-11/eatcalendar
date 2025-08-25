package app.eat.controller;

import app.eat.model.MealPlan;
import app.eat.model.User;
import app.eat.service.MealPlanService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mealplans")
public class MealPlanController {
    private final MealPlanService service;

    public MealPlanController(MealPlanService service) {
        this.service = service;
    }

    @GetMapping("/{userId}/weekly")
    public List<MealPlan> getWeeklyPlan(@PathVariable Long userId) {
        // per semplicità qui recupero i meal plan settimanali da service
        return service.findByUser(userId);
    }

    @GetMapping
    public List<MealPlan> getAll() {
        return service.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<MealPlan> getByUser(@PathVariable Long userId) {
        return service.findByUser(userId);
    }

    @PostMapping
    public MealPlan create(@RequestBody MealPlan m, @RequestParam Long userId) {
        return service.saveForUser(m, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}