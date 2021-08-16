package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.domain.Recipe;
import guru.springframework.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IndexControllerTest {
    IndexController indexController;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);

    }

    @Test
    void listRecipes() {
        //Given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(Recipe.builder().id(4L).description("desc").build());
        recipes.add(Recipe.builder().id(5L).description("desc 2").build());
        Mockito.when(recipeService.getAllRecipe()).thenReturn(recipes);
        //When
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        String viewName = indexController.listRecipes(model);

        //Then
        assertEquals("recipe/list", viewName);
        Mockito.verify(recipeService, Mockito.times(1)).getAllRecipe();
        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
        Set<Recipe> resultIngredient = argumentCaptor.getValue();
        assertEquals(2, resultIngredient.size());
    }
}