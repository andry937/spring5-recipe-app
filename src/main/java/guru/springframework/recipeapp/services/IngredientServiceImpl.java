package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.domain.Ingredient;
import guru.springframework.recipeapp.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Set<Ingredient> findByRecipeId(Long id) {
        return ingredientRepository.findByRecipeId(id);
    }
}
