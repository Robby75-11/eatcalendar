package app.eat.model;

import app.eat.enumeration.GiornoSettimana;
import app.eat.enumeration.TipoPasto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MealPlan {
    @Id
    @GeneratedValue(strategy =   GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GiornoSettimana giorno;

    @Enumerated(EnumType.STRING)
    private TipoPasto pasto;

    @ManyToOne
    private Recipe recipe;

    @ManyToOne
    private User user;

}
