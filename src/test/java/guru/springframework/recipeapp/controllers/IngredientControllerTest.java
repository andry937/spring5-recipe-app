package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.services.IngredientService;
import guru.springframework.recipeapp.services.RecipeService;
import guru.springframework.recipeapp.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class IngredientControllerTest {
    IngredientController ingredientController;
    @Mock
    IngredientService ingredientService;
    @Mock
    UnitOfMeasureService unitOfMeasureService;
    @Mock
    RecipeService recipeService;

    MockMvc mockMvc;

    Long recipeId = 1L;
    Long ingredientId = 1L;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        ingredientController = new IngredientController(ingredientService, unitOfMeasureService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
        Mockito.when(ingredientService.findById(Mockito.anyLong())).thenReturn(new IngredientCommand());
    }

    @Test
    void testListIngredient() throws Exception {
        mockMvc.perform(get("/recipes/"+recipeId+"/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/list"))
                .andExpect(model().attributeExists("ingredients"));
    }

    @Test
    void testUpdateIngredient() throws Exception {
        mockMvc.perform(get("/ingredients/"+ingredientId+"/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/form"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uoms"));
    }
}
