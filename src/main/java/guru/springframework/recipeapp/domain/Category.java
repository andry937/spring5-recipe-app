package guru.springframework.recipeapp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category extends BaseDomain{
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
