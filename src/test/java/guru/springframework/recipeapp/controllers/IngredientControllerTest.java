package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.services.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class IngredientControllerTest {
    IngredientController ingredientController;
    @Mock
    IngredientService ingredientService;
    MockMvc mockMvc;

    Long recipeId = 1L;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        ingredientController = new IngredientController(ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void testListIngredient() throws Exception {
        mockMvc.perform(get("/recipes/"+recipeId+"/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/list"))
                .andExpect(model().attributeExists("ingredients"));
    }
}
