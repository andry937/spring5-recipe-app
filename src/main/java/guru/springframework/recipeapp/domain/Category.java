package guru.springframework.recipeapp.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Category extends BaseDomain{
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
}
