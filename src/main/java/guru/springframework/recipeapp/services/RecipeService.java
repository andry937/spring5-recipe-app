package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.RecipeCommand;

import java.util.Set;


public interface RecipeService {

    Set<RecipeCommand> getAllRecipe();
    RecipeCommand getRecipe(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteById(Long id);
}
