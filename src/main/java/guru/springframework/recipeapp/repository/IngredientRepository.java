package guru.springframework.recipeapp.repository;

import guru.springframework.recipeapp.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Set<Ingredient> findByRecipeId(Long id);

}
