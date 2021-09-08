package guru.springframework.recipeapp.converters;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipeOnly implements Converter<RecipeCommand, Recipe> {
    private final NotesCommandToNotes notesConvert;

    public RecipeCommandToRecipeOnly(NotesCommandToNotes notesConvert) {
        this.notesConvert = notesConvert;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand == null) {
            return null;
        }
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
                .notes(notesConvert.convert(recipeCommand.getNotes()))
                .build();
    }
}
