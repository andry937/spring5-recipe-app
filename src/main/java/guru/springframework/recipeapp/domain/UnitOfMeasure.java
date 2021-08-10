package guru.springframework.recipeapp.domain;

import javax.persistence.Entity;

@Entity
public class UnitOfMeasure extends BaseDomain{
    private String uom;

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}
