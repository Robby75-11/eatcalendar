package app.eat.repository;

import app.eat.model.MealPlan;
import app.eat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealPlanRepository  extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByUser(User user);
}
