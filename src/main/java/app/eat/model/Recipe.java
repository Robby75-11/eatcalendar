package app.eat.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Recipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String titolo;

    @Column(length = 2000)
    private String descrizione;

    @ElementCollection
    private List<String> ingredienti;

    private String immagineUrl;
}
