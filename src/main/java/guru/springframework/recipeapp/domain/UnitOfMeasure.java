package guru.springframework.recipeapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UnitOfMeasure extends BaseDomain{
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @Builder
    public UnitOfMeasure(Long id, String description) {
        super(id);
        this.description = description;
    }
}
