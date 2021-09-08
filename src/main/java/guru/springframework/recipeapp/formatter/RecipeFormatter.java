package guru.springframework.recipeapp.formatter;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.services.RecipeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class RecipeFormatter implements Formatter<RecipeCommand> {
    private final RecipeService recipeService;

    public RecipeFormatter(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public RecipeCommand parse(String id, Locale locale) {
        return recipeService.getRecipe(Long.valueOf(id));
    }

    @Override
    public String print(RecipeCommand recipe, Locale locale) {
        return recipe != null ? recipe.getId().toString() : "";
    }
}
