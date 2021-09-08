package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;
import guru.springframework.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RecipeServiceImplTest {
    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);
        Mockito.when(recipeCommandToRecipe.convert(Mockito.any(RecipeCommand.class))).thenReturn(new Recipe());
        Mockito.when(recipeToRecipeCommand.convert(Mockito.any(Recipe.class))).thenReturn(new RecipeCommand());
        Mockito.when(recipeRepository.save(Mockito.any(Recipe.class))).thenReturn(new Recipe());
    }
    @Test
    void getAllRecipe() {
        Set<Recipe> recipes = recipeService.getAllRecipe();
        assertEquals(1, recipes.size());
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void createOrUpdateRecipe(){
        RecipeCommand recipe = RecipeCommand.builder().build();
        RecipeCommand saved = recipeService.saveRecipeCommand(recipe);
        assertNotNull(saved);
        Mockito.verify(recipeRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void deleteRecipe(){
        Long id = 1L;
        recipeService.deleteById(id);
        Mockito.verify(recipeRepository, Mockito.times(1)).deleteById(Mockito.any());
    }
}