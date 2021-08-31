package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter) {
        this.unitOfMeasureConverter = unitOfMeasureConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand == null) {
            return null;
        }
        return Ingredient.builder()
                .id(ingredientCommand.getId())
                .amount(ingredientCommand.getAmount())
                .description(ingredientCommand.getDescription())
                .unitOfMeasure(unitOfMeasureConverter.convert(ingredientCommand.getUnitOfMeasure()))
                .build();
    }
}
