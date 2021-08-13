package guru.springframework.recipeapp.bootstrap;

import guru.springframework.recipeapp.domain.*;
import guru.springframework.recipeapp.repository.CategoryRepository;
import guru.springframework.recipeapp.repository.RecipeRepository;
import guru.springframework.recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class BootStrapData implements CommandLineRunner {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public BootStrapData(UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    public String loadFile(String path) throws IOException {
        File resource = new ClassPathResource(path).getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }

    @Override
    public void run(String... args) throws Exception {
        Category mexican = categoryRepository.findByDescription("Mexican").get();
        Set<Category> categories = new HashSet<>();
        categories.add(mexican);

        //Guacamole recipe
        Map<String, UnitOfMeasure> maps = new HashMap<>();
        Set<Ingredient> ingredients = new HashSet<>();

        Notes notes = new Notes();
        notes.setRecipeNotes(loadFile("data/guacamole-note.txt"));

        Recipe guacamole = new Recipe();
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setCategories(categories);
        guacamole.setIngredients(ingredients);
        guacamole.setDescription("the Best Guacamole");
        guacamole.setDirections(loadFile("data/guacamole.txt"));
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setNotes(notes);

        this.unitOfMeasureRepository.findAll().forEach(unitOfMeasure -> maps.put(unitOfMeasure.getDescription(), unitOfMeasure));
        //		2 ripe avocados
        Ingredient avocados = new Ingredient();
        avocados.setDescription("Avocados");
        avocados.setAmount(new BigDecimal(2));
        avocados.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(avocados);
        //		1/4 Teaspoon salt, plus more to taste
        Ingredient salt = new Ingredient();
        salt.setDescription("salt, plus more to taste");
        salt.setAmount(new BigDecimal(0.25));
        salt.setUnitOfMeasure(maps.get("Teaspoon"));
        ingredients.add(salt);
        //		1 Tablespoon fresh lime or lemon juice
        Ingredient lime = new Ingredient();
        lime.setDescription("fresh lime or lemon juice");
        lime.setAmount(new BigDecimal(1));
        lime.setUnitOfMeasure(maps.get("Tablespoon"));
        ingredients.add(lime);
        //		2-4 Tablespoon minced red onion or thinly sliced green onion
        Ingredient onion = new Ingredient();
        onion.setDescription("minced red onion or thinly sliced green onion");
        onion.setAmount(new BigDecimal(4));
        onion.setUnitOfMeasure(maps.get("Tablespoon"));
        ingredients.add(onion);
        //		1-2 serrano (or jalapeño) chilis, stems and seeds removed, minced
        Ingredient serrano = new Ingredient();
        serrano.setDescription("serrano (or jalapeño) chilis, stems and seeds removed, minced");
        serrano.setAmount(new BigDecimal(2));
        serrano.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(serrano);
        //		2 Tablespoon cilantro (leaves and tender stems), finely chopped
        Ingredient cilantro = new Ingredient();
        cilantro.setDescription("cilantro (leaves and tender stems), finely chopped");
        cilantro.setAmount(new BigDecimal(2));
        cilantro.setUnitOfMeasure(maps.get("Tablespoon"));
        ingredients.add(cilantro);
        //		Pinch freshly ground black pepper
        Ingredient pepper = new Ingredient();
        pepper.setDescription("freshly ground black pepper");
        pepper.setAmount(new BigDecimal(2));
        pepper.setUnitOfMeasure(maps.get("Pinch"));
        ingredients.add(pepper);
        //		1/2 ripe tomato, chopped (optional)
        Ingredient tomato = new Ingredient();
        tomato.setDescription("ripe tomato, chopped (optional)");
        tomato.setAmount(new BigDecimal(0.5));
        tomato.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(tomato);
        //		Red radish or jicama slices for garnish (optional)
        Ingredient radish = new Ingredient();
        radish.setDescription("red radish or jicama slices for garnish (optional)");
        radish.setAmount(new BigDecimal(1));
        radish.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(radish);
        ingredients.forEach(ingredient -> ingredient.setRecipe(guacamole));
        recipeRepository.save(guacamole);

        //Chicken recipe
        Category american = categoryRepository.findByDescription("American").get();
        categories = new HashSet<>();
        categories.add(american);
        ingredients = new HashSet<>();

        notes = new Notes();
        notes.setRecipeNotes(loadFile("data/chicken-note.txt"));

        Recipe chicken = new Recipe();
        chicken.setPrepTime(20);
        chicken.setCookTime(15);
        chicken.setServings(6);
        chicken.setCategories(categories);
        chicken.setIngredients(ingredients);
        chicken.setDescription("Spicy Grilled Chicken Tacos");
        chicken.setDirections(loadFile("data/chicken.txt"));
        chicken.setDifficulty(Difficulty.MODERATE);
        chicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chicken.setNotes(notes);
//        For the chicken:
//        2 Tablespoon ancho chili powder
        Ingredient ancho = new Ingredient();
        ancho.setDescription("ancho chili powder");
        ancho.setAmount(new BigDecimal(2));
        ancho.setUnitOfMeasure(maps.get("Tablespoon"));
        ingredients.add(ancho);
//        1 Teaspoon dried oregano
        Ingredient oregano = new Ingredient();
        oregano.setDescription("dried oregano");
        oregano.setAmount(new BigDecimal(2));
        oregano.setUnitOfMeasure(maps.get("Teaspoon"));
        ingredients.add(oregano);
//        1 Teaspoon dried cumin
        Ingredient cumin = new Ingredient();
        cumin.setDescription("dried cumin");
        cumin.setAmount(new BigDecimal(1));
        cumin.setUnitOfMeasure(maps.get("Teaspoon"));
        ingredients.add(cumin);
//        1 Teaspoon sugar
        Ingredient sugar = new Ingredient();
        sugar.setDescription("sugar");
        sugar.setAmount(new BigDecimal(1));
        sugar.setUnitOfMeasure(maps.get("Teaspoon"));
        ingredients.add(sugar);
//        1/2 Teaspoon salt
        salt = new Ingredient();
        salt.setDescription("salt");
        salt.setAmount(new BigDecimal(0.5));
        salt.setUnitOfMeasure(maps.get("Teaspoon"));
        ingredients.add(salt);
//        1 clove garlic, finely chopped
        Ingredient garlic = new Ingredient();
        garlic.setDescription("clove garlic, finely chopped");
        garlic.setAmount(new BigDecimal(1));
        garlic.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(garlic);
//        1 Tablespoon finely grated orange zest
        Ingredient orange = new Ingredient();
        orange.setDescription("finely grated orange zest");
        orange.setAmount(new BigDecimal(1));
        orange.setUnitOfMeasure(maps.get("Tablespoon"));
        ingredients.add(orange);
//        3 Tablespoon fresh-squeezed orange juice
        orange = new Ingredient();
        orange.setDescription("fresh-squeezed orange juice");
        orange.setAmount(new BigDecimal(3));
        orange.setUnitOfMeasure(maps.get("Tablespoon"));
        ingredients.add(orange);
//        2 Tablespoon olive oil
        Ingredient olive = new Ingredient();
        olive.setDescription("olive oil");
        olive.setAmount(new BigDecimal(2));
        olive.setUnitOfMeasure(maps.get("Tablespoon"));
        ingredients.add(olive);
//        4 to 6 skinless, boneless chicken thighs (1 1/4 pounds)
        Ingredient bonelessChicken = new Ingredient();
        bonelessChicken.setDescription("skinless, boneless chicken thighs (1 1/4 pounds)");
        bonelessChicken.setAmount(new BigDecimal(6));
        bonelessChicken.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(olive);
//        To serve:
//        8 small corn tortillas
        Ingredient tortillas = new Ingredient();
        tortillas.setDescription("small corn tortillas");
        tortillas.setAmount(new BigDecimal(8));
        tortillas.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(tortillas);
//        3 Cups packed baby arugula (3 ounces)
        Ingredient arugula = new Ingredient();
        arugula.setDescription("packed baby arugula (3 ounces)");
        arugula.setAmount(new BigDecimal(8));
        arugula.setUnitOfMeasure(maps.get("Cup"));
        ingredients.add(arugula);
//        2 medium ripe avocados, sliced
        Ingredient ripeAvocados = new Ingredient();
        ripeAvocados.setDescription("medium ripe avocados, sliced");
        ripeAvocados.setAmount(new BigDecimal(2));
        ripeAvocados.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(ripeAvocados);
//        4 radishes, thinly sliced
        Ingredient radishes = new Ingredient();
        radishes.setDescription("radishes, thinly sliced");
        radishes.setAmount(new BigDecimal(4));
        radishes.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(radishes);
//        1/2 pint cherry tomatoes, halved
        Ingredient tomatoes = new Ingredient();
        tomatoes.setDescription("cherry tomatoes, halved");
        tomatoes.setAmount(new BigDecimal(0.5));
        tomatoes.setUnitOfMeasure(maps.get("Pint"));
        ingredients.add(tomatoes);
//        1/4 red onion, thinly sliced
        onion = new Ingredient();
        onion.setDescription("red onion, thinly sliced");
        onion.setAmount(new BigDecimal(0.25));
        onion.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(onion);
//        Roughly chopped cilantro
        cilantro = new Ingredient();
        cilantro.setDescription("Roughly chopped cilantro");
        cilantro.setAmount(new BigDecimal(1));
        cilantro.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(cilantro);
//        1/2 Cup sour cream thinned with 1/4 Cup milk
        Ingredient milk = new Ingredient();
        milk.setDescription("sour cream thinned with 1/4 Cup milk");
        milk.setAmount(new BigDecimal(0.5));
        milk.setUnitOfMeasure(maps.get("Cup"));
        ingredients.add(milk);
//        1 lime, cut into wedges
        lime = new Ingredient();
        lime.setDescription("lime, cut into wedges");
        lime.setAmount(new BigDecimal(1));
        lime.setUnitOfMeasure(maps.get("Each"));
        ingredients.add(lime);

        ingredients.forEach(ingredient -> ingredient.setRecipe(chicken));
        recipeRepository.save(chicken);
    }
}
