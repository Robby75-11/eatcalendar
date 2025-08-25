package app.eat.model;

import app.eat.enumeration.GiornoSettimana;
import app.eat.enumeration.TipoPasto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name = "recipe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Recipe recipe;

    @ManyToOne
    private User user;

}
