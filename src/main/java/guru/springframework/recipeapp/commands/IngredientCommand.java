package guru.springframework.recipeapp.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand extends BaseCommand {
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
    @Positive
    private BigDecimal amount;
    @NotNull
    private RecipeCommand recipe;
    @NotNull
    private UnitOfMeasureCommand unitOfMeasure;

    @Builder
    public IngredientCommand(Long id, String description, BigDecimal amount, RecipeCommand recipe, UnitOfMeasureCommand unitOfMeasure) {
        super(id);
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
        this.unitOfMeasure = unitOfMeasure;
    }
}
