package guru.springframework.recipeapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Ingredient extends BaseDomain{
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
    @Positive
    private BigDecimal amount;

    @ManyToOne
    @NotNull
    private Recipe recipe;

    @OneToOne
    @NotNull
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
