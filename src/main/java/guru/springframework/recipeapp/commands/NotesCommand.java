package guru.springframework.recipeapp.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand extends BaseCommand {
    @NotBlank
    private RecipeCommand recipe;
    private String recipeNotes;

    @Builder
    public NotesCommand(Long id, RecipeCommand recipe, String recipeNotes) {
        super(id);
        this.recipe = recipe;
        this.recipeNotes = recipeNotes;
    }
}
