package app.eat.dto;

import app.eat.enumeration.GiornoSettimana;
import app.eat.enumeration.TipoPasto;
import lombok.Data;

@Data
public class MealPlanDto {

    private Long id;
    private GiornoSettimana giorno;
    private TipoPasto pasto;
    private Long recipeId;   // solo id per semplicità
    private Long userId;
}
