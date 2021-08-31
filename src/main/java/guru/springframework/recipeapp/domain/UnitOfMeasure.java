package guru.springframework.recipeapp.domain;

import lombok.*;

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
