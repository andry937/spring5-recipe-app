package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.recipeapp.converters.IngredientToIngredientCommand;
import guru.springframework.recipeapp.domain.Ingredient;
import guru.springframework.recipeapp.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public Set<Ingredient> findByRecipeId(Long id) {
        return ingredientRepository.findByRecipeId(id);
    }

    @Override
    @Transactional
    public IngredientCommand findById(Long id) {
        return this.ingredientToIngredientCommand.convert(ingredientRepository.findById(id).orElse(null));
    }


    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Ingredient detached = ingredientCommandToIngredient.convert(command);
        Ingredient saved = ingredientRepository.save(detached);
        return ingredientToIngredientCommand.convert(saved);
    }
}
