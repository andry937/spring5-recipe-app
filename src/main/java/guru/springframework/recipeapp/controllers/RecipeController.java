package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Difficulty;
import guru.springframework.recipeapp.services.CategoryService;
import guru.springframework.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/recipes")
@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping({"/","","/index","/index.html"})
    public String listRecipes(Model model){
        model.addAttribute("recipes", recipeService.getAllRecipe());
        return "recipe/list";
    }

    @RequestMapping("/{id}")
    public String getRecipeDetails(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.getRecipe(Long.valueOf(id)));
        return "recipe/details";
    }

    @RequestMapping("/create")
    public String createRecipe(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/form";
    }

    @RequestMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("recipe", recipeService.getRecipeCommand(Long.valueOf(id)));
        return "recipe/form";
    }

    @PostMapping({"/",""})
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(command);
        return "redirect:/recipes/"+savedRecipe.getId();
    }
}
