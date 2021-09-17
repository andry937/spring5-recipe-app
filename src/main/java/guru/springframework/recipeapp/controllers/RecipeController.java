package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.commands.CategoryCommand;
import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.exceptions.NotFoundException;
import guru.springframework.recipeapp.services.CategoryService;
import guru.springframework.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;

@RequestMapping("/recipes")
@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public Set<CategoryCommand> populateCategories(){
        return categoryService.getAllCategories();
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
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/form";
    }

    @RequestMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.getRecipe(Long.valueOf(id)));
        return "recipe/form";
    }

    @PostMapping(value = {"/",""},consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command,
                                   BindingResult result,
                                   @RequestPart("recipe-image") MultipartFile image,
                                   Model model) throws IOException {
        if(result.hasErrors()){
            model.addAttribute("recipe", command);
            result.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return "recipe/form";
        }
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(command, image);
        return "redirect:/recipes/"+savedRecipe.getId();
    }

    @RequestMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/recipes";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(){
        log.error("Handling not found exception");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        return modelAndView;
    }
}
