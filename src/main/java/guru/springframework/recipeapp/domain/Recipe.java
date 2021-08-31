package guru.springframework.recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Recipe extends BaseDomain {
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @Lob
    private Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @Builder
    public Recipe(Long id, String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url, String directions, Byte[] image, Notes notes, Difficulty difficulty, Set<Ingredient> ingredients, Set<Category> categories) {
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
}
