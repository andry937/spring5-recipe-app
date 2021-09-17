package guru.springframework.recipeapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UnitOfMeasure extends BaseDomain{
    private String description;

    @Builder
    public UnitOfMeasure(Long id, String description) {
        super(id);
        this.description = description;
    }
}
