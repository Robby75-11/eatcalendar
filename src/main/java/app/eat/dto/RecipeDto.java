package app.eat.dto;

import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;
@Data
public class RecipeDto {
    private Long id;
    private String titolo;
    private String descrizione;

    @ElementCollection
    private List<String> ingredienti;

    private String immagineUrl;
}
