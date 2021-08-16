package guru.springframework.recipeapp.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class UnitOfMeasure extends BaseDomain{
    private String description;

}
