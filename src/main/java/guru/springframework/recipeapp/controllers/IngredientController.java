package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.commands.IngredientCommand;
import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.domain.Ingredient;
import guru.springframework.recipeapp.domain.UnitOfMeasure;
import guru.springframework.recipeapp.services.IngredientService;
import guru.springframework.recipeapp.services.RecipeService;
import guru.springframework.recipeapp.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
public class IngredientController {
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final RecipeService recipeService;

    public IngredientController(IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipes/{id}/ingredients")
    public String showRecipeIngredients(@PathVariable String id, Model model){
        Set<Ingredient> ingredients = ingredientService.findByRecipeId(Long.valueOf(id));
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("recipe", ingredients.stream().findFirst().orElse(new Ingredient()).getRecipe());
        return "ingredient/list";
    }

    @RequestMapping("/ingredients/{id}/update")
    public String updateIngredient(@PathVariable String id, Model model){
        IngredientCommand ingredient = ingredientService.findById(Long.valueOf(id));
        Set<UnitOfMeasure> unitOfMeasures = unitOfMeasureService.findAll();
        Set<RecipeCommand> recipeCommands = recipeService.getAllRecipe();
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("uoms", unitOfMeasures);
        model.addAttribute("recipes", recipeCommands);
        return "ingredient/form";
    }

    @PostMapping("/ingredients")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand saved = ingredientService.saveIngredientCommand(command);
        return "redirect:/recipes/"+command.getRecipe().getId()+"/ingredients";
    }
}
