package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.domain.Ingredient;

import java.util.Set;

public interface IngredientService {
    Set<Ingredient> findByRecipeId(Long id);
    IngredientCommand findById(Long valueOf);

    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
