package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.domain.Recipe;


public interface RecipeService {

    Iterable<Recipe> getAllRecipe();

    Recipe getRecipe(Long id);
}
