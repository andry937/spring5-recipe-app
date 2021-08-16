package guru.springframework.recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category extends BaseDomain{
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
}
