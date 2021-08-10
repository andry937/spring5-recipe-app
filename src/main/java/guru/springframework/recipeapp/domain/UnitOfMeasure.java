package guru.springframework.recipeapp.domain;

import javax.persistence.Entity;

@Entity
public class UnitOfMeasure extends BaseDomain{
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
