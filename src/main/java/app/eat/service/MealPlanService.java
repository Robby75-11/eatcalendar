package app.eat.service;

import app.eat.model.MealPlan;
import app.eat.model.User;
import app.eat.repository.MealPlanRepository;
import app.eat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealPlanService {
    private final MealPlanRepository repo;
    private final UserRepository userRepo;

    public MealPlanService(MealPlanRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    public List<MealPlan> findAll() {
        return repo.findAll();
    }

    public List<MealPlan> findByUser(Long userId) {
        User u = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return repo.findByUser(u);
    }
    public MealPlan saveForUser(MealPlan m, Long userId) {
        User u = userRepo.findById(userId).orElseThrow();
        m.setUser(u);
        return repo.save(m);
    }


    public MealPlan save(MealPlan m) {
        return repo.save(m);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}