package app.eat.service;

import app.eat.model.Recipe;
import app.eat.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository repo;
    private final ImmagineService immagineService;

    public RecipeService(RecipeRepository repo, ImmagineService immagineService) {
        this.repo = repo;
        this.immagineService = immagineService;
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
    // 🔹 Caricamento immagini e associazione alla ricetta
    public Recipe addImagesToRecipe(Long id, List<MultipartFile> files) throws IOException {
        Recipe recipe = findById(id);

        if (!files.isEmpty()) {
            String imageUrl = immagineService.caricaImmagine(files.get(0)); // carica su Cloudinary
            recipe.setImmagineUrl(imageUrl); // aggiunge alla lista immagini
        }

        return repo.save(recipe);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}