package guru.springframework.recipeapp.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Data
@Entity
public class Notes extends BaseDomain {
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;

}
