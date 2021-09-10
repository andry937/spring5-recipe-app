package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.CategoryCommand;
import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final CategoryToCategoryCommand categoryConvert;
    private final IngredientToIngredientCommand ingredientConvert;
    private final NotesToNotesCommand notesConvert;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConvert,
                                 IngredientToIngredientCommand ingredientConvert,
                                 NotesToNotesCommand notesConvert) {
        this.categoryConvert = categoryConvert;
        this.ingredientConvert = ingredientConvert;
        this.notesConvert = notesConvert;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null) {
            return null;
        }
        Set<CategoryCommand> categories = recipe.getCategories().stream()
                .map(categoryConvert::convert)
                .collect(Collectors.toSet());
        Set<IngredientCommand> ingredients = recipe.getIngredients().stream()
                .map(ingredientConvert::convert)
                .collect(Collectors.toSet());
        return RecipeCommand.builder()
                .id(recipe.getId())
                .description(recipe.getDescription())
                .difficulty(recipe.getDifficulty())
                .directions(recipe.getDirections())
                .cookTime(recipe.getCookTime())
                .prepTime(recipe.getPrepTime())
                .image(recipe.getImage())
                .servings(recipe.getServings())
                .source(recipe.getSource())
                .url(recipe.getUrl())
                .categories(categories)
                .ingredients(ingredients)
                .notes(notesConvert.convert(recipe.getNotes()))
                .build();
    }
}
