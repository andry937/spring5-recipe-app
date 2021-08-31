package guru.springframework.recipeapp.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand extends BaseCommand {
    private String description;

    @Builder
    public CategoryCommand(Long id, String description) {
        super(id);
        this.description = description;
    }
}
