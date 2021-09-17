package guru.springframework.recipeapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category extends BaseDomain{
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    @Builder
    public Category(Long id, String description, Set<Recipe> recipes) {
        super(id);
        this.description = description;
        this.recipes = recipes;
    }
}
