package app.eat.repository;

import app.eat.model.MealPlan;
import app.eat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealPlanRepository  extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByUser(User user);

    @Modifying
    @Query("DELETE FROM MealPlan m WHERE m.recipe.id = :recipeId")
    void deleteByRecipeId(@Param("recipeId") Long recipeId);
}
