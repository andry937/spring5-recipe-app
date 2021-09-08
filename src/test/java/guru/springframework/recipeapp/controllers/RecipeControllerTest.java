package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;
import guru.springframework.recipeapp.services.CategoryService;
import guru.springframework.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RecipeControllerTest {
    RecipeController recipeController;
    @Mock
    RecipeService recipeService;
    @Mock
    CategoryService categoryService;
    @Mock
    Model model;

    Set<Recipe> recipes;

    Long id = 4L;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeController = new RecipeController(recipeService, categoryService);

        recipes = new HashSet<>();
        recipes.add(Recipe.builder().id(id).description("desc").build());
        recipes.add(Recipe.builder().id(5L).description("desc 2").build());

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        Mockito.when(recipeService.getRecipe(Mockito.anyLong())).thenReturn(new Recipe());
        Mockito.when(recipeService.saveRecipeCommand(Mockito.any(RecipeCommand.class)))
                .thenReturn(RecipeCommand.builder().id(id).build());
        Mockito.when(recipeService.getRecipeCommand(Mockito.anyLong())).thenReturn(new RecipeCommand());

    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/list"));
    }

    @Test
    void listRecipes() {
        //Given
        Mockito.when(recipeService.getAllRecipe()).thenReturn(recipes);
        //When
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        String viewName = recipeController.listRecipes(model);

        //Then
        assertEquals("recipe/list", viewName);
        Mockito.verify(recipeService, Mockito.times(1)).getAllRecipe();
        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
        Set<Recipe> resultIngredient = argumentCaptor.getValue();
        assertEquals(2, resultIngredient.size());
    }

    @Test
    void showRecipe() throws Exception {
        mockMvc.perform(get("/recipes/"+id))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/details"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void createRecipe() throws Exception {
        mockMvc.perform(get("/recipes/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/form"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    void updateRecipe() throws Exception {
        mockMvc.perform(get("/recipes/"+id+"/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/form"))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    void createOrUpdateRecipe() throws Exception{
        mockMvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("description", "some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes/"+id));
    }

    @Test
    void deleteRecipe() throws Exception {
        mockMvc.perform(get("/recipes/"+id+"/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipes"));
        Mockito.verify(recipeService, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}