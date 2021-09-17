package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.RecipeCommand;
import guru.springframework.recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.recipeapp.domain.Recipe;
import guru.springframework.recipeapp.exceptions.NotFoundException;
import guru.springframework.recipeapp.repository.RecipeRepository;
import guru.springframework.recipeapp.utility.ByteUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    @Transactional
    public Set<RecipeCommand> getAllRecipe() {
        Set<RecipeCommand> recipes = new HashSet<>();
        this.recipeRepository.findAll().forEach(recipe -> recipes.add(recipeToRecipeCommand.convert(recipe)));
        return recipes;
    }

    @Override
    @Transactional
    public RecipeCommand getRecipe(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow(()-> new NotFoundException("Recipe Not Found. For ID value: " + id));
        return recipeToRecipeCommand.convert(recipe);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        try {
            return saveRecipeCommand(recipeCommand, null);
        } catch (IOException e) {
            //Exception ignored
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand, MultipartFile image) throws IOException {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        if(image != null){
            Byte[] bytesImage = ByteUtility.toObjects(image.getBytes());
            detachedRecipe.setImage(bytesImage);
        }
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
