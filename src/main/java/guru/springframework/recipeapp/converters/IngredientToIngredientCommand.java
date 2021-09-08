package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConvert;
    private final RecipeOnlyToRecipeCommand recipeCommand;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConvert, RecipeOnlyToRecipeCommand recipeCommand) {
        this.unitOfMeasureConvert = unitOfMeasureConvert;
        this.recipeCommand = recipeCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null) {
            return null;
        }
        return IngredientCommand.builder()
                .id(ingredient.getId())
                .description(ingredient.getDescription())
                .amount(ingredient.getAmount())
                .unitOfMeasure(unitOfMeasureConvert.convert(ingredient.getUnitOfMeasure()))
                .recipe(recipeCommand.convert(ingredient.getRecipe()))
                .build();
    }
}
