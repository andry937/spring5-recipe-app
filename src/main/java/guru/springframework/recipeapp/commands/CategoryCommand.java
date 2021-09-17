package guru.springframework.recipeapp.commands;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand extends BaseCommand {
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @Builder
    public CategoryCommand(Long id, String description) {
        super(id);
        this.description = description;
    }
}
