package guru.springframework.recipeapp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Ingredient extends BaseDomain{
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private UnitOfMeasure unitOfMeasure;

    @Builder
    public Ingredient(Long id, String description, BigDecimal amount, Recipe recipe, UnitOfMeasure unitOfMeasure) {
        super(id);
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
        this.unitOfMeasure = unitOfMeasure;
    }
}
