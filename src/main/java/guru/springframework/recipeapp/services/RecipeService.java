package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.RecipeCommand;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;


public interface RecipeService {

    Set<RecipeCommand> getAllRecipe();
    RecipeCommand getRecipe(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand, MultipartFile image) throws IOException;

    void deleteById(Long id);
}
