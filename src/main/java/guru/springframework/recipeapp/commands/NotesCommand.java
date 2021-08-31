package guru.springframework.recipeapp.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand extends BaseCommand {
    private RecipeCommand recipe;
    private String recipeNotes;

    @Builder
    public NotesCommand(Long id, RecipeCommand recipe, String recipeNotes) {
        super(id);
        this.recipe = recipe;
        this.recipeNotes = recipeNotes;
    }
}
