package app.eat.service;

import app.eat.model.Recipe;
import app.eat.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository repo;

    public RecipeService(RecipeRepository repo) {
        this.repo = repo;
    }

    public List<Recipe> findAll() {
        return repo.findAll();
    }

    public Recipe save(Recipe r) {
        return repo.save(r);
    }

    public Recipe findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}