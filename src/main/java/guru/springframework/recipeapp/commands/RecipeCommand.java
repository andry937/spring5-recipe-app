package guru.springframework.recipeapp.commands;

import guru.springframework.recipeapp.domain.Difficulty;
import guru.springframework.recipeapp.utility.ByteUtility;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand extends BaseCommand {
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Byte[] image;
    private NotesCommand notes;
    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();

    @Builder
    public RecipeCommand(Long id, String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url, String directions, Byte[] image, NotesCommand notes, Difficulty difficulty, Set<IngredientCommand> ingredients, Set<CategoryCommand> categories) {
        super(id);
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.directions = directions;
        this.image = image;
        this.notes = notes;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
        this.categories = categories;
    }

    public String getImageUrl() {
        if(this.image == null){
            return "";
        }
        try {
            return "data:image/jpeg;base64," + ByteUtility.toBase64(this.image);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
