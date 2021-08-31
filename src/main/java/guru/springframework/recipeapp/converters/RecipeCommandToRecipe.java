package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Category;
import guru.springframework.recipeapp.domain.Ingredient;
import guru.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private final CategoryCommandToCategory categoryConvert;
    private final IngredientCommandToIngredient ingredientConvert;
    private final NotesCommandToNotes notesConvert;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConvert,
                                 IngredientCommandToIngredient ingredientConvert,
                                 NotesCommandToNotes notesConvert) {
        this.categoryConvert = categoryConvert;
        this.ingredientConvert = ingredientConvert;
        this.notesConvert = notesConvert;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand == null) {
            return null;
        }
        Set<Category> categories = recipeCommand.getCategories().stream()
                .map(categoryConvert::convert)
                .collect(Collectors.toSet());
        Set<Ingredient> ingredients = recipeCommand.getIngredients().stream()
                .map(ingredientConvert::convert)
                .collect(Collectors.toSet());
        return Recipe.builder()
                .id(recipeCommand.getId())
                .description(recipeCommand.getDescription())
                .difficulty(recipeCommand.getDifficulty())
                .directions(recipeCommand.getDirections())
                .cookTime(recipeCommand.getCookTime())
                .prepTime(recipeCommand.getPrepTime())
                .servings(recipeCommand.getServings())
                .source(recipeCommand.getSource())
                .url(recipeCommand.getUrl())
                .categories(categories)
                .ingredients(ingredients)
                .notes(notesConvert.convert(recipeCommand.getNotes()))
                .build();
    }
}
