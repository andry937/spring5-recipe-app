package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.domain.Ingredient;
import guru.springframework.recipeapp.domain.Recipe;
import guru.springframework.recipeapp.services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/recipes/{id}/ingredients")
    public String showRecipeIngredients(@PathVariable String id, Model model){
        Set<Ingredient> ingredients = ingredientService.findByRecipeId(Long.valueOf(id));
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("recipe", ingredients.stream().findFirst().orElse(new Ingredient()).getRecipe());
        return "ingredient/list";
    }
}
