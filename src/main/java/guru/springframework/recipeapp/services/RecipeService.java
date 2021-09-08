package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;

import java.util.Set;


public interface RecipeService {

    Set<Recipe> getAllRecipe();
    Recipe getRecipe(Long id);
    RecipeCommand getRecipeCommand(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
