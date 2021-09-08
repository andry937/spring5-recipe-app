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
public class RecipeOnlyToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private final NotesToNotesCommand notesConvert;

    public RecipeOnlyToRecipeCommand(NotesToNotesCommand notesConvert) {
        this.notesConvert = notesConvert;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null) {
            return null;
        }
        return RecipeCommand.builder()
                .id(recipe.getId())
                .description(recipe.getDescription())
                .difficulty(recipe.getDifficulty())
                .directions(recipe.getDirections())
                .cookTime(recipe.getCookTime())
                .prepTime(recipe.getPrepTime())
                .servings(recipe.getServings())
                .source(recipe.getSource())
                .url(recipe.getUrl())
                .notes(notesConvert.convert(recipe.getNotes()))
                .build();
    }
}
