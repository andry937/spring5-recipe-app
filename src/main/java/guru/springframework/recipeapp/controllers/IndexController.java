package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/","","/index","/index.html"})
    public String listRecipes(Model model){
        model.addAttribute("recipes", recipeService.getAllRecipe());
        return "recipe/list";
    }

    @RequestMapping("/recipes/{id}")
    public String getRecipeDetails(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.getRecipe(Long.valueOf(id)));
        return "recipe/details";
    }
}
