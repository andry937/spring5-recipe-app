package guru.springframework.recipeapp.repository;

import guru.springframework.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecipeRepository extends CrudRepository<Recipe, UUID> {
}
