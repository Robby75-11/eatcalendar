package app.eat.service;

import app.eat.model.Recipe;
import app.eat.repository.MealPlanRepository;
import app.eat.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepo;
    private final ImmagineService immagineService;
    private final MealPlanRepository mealPlanRepo;

    public RecipeService(RecipeRepository recipeRepo, ImmagineService immagineService, MealPlanRepository mealPlanRepo) {
        this.recipeRepo = recipeRepo;
        this.immagineService = immagineService;
        this.mealPlanRepo = mealPlanRepo;
    }

    public List<Recipe> findAll() {
        return recipeRepo.findAll();
    }

    public Recipe save(Recipe r) {
        return recipeRepo.save(r);
    }

    public Recipe findById(Long id) {
        return recipeRepo.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public Recipe update(Long id, Recipe updatedRecipe) {
        Recipe existing = findById(id); // recupera la ricetta esistente

        existing.setTitolo(updatedRecipe.getTitolo());
        existing.setDescrizione(updatedRecipe.getDescrizione());
        existing.setIngredienti(updatedRecipe.getIngredienti());

               return recipeRepo.save(existing);
    }


    // 🔹 Caricamento immagini e associazione alla ricetta
    public Recipe addImagesToRecipe(Long id, List<MultipartFile> files) throws IOException {
        Recipe recipe = findById(id);

        if (!files.isEmpty()) {
            String imageUrl = immagineService.caricaImmagine(files.get(0)); // carica su Cloudinary
            recipe.setImmagineUrl(imageUrl); // aggiunge alla lista immagini
        }

        return recipeRepo.save(recipe);
    }

    @Transactional // Aggiungi questa annotazione
    public void delete(Long id) {
        mealPlanRepo.deleteByRecipeId(id); // Elimina prima tutti i MealPlan che utilizzano questa ricetta
        recipeRepo.deleteById(id); // Poi elimina la ricetta
    }
}