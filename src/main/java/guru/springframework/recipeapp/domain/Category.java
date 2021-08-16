package guru.springframework.recipeapp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
public class Category extends BaseDomain{
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
}
