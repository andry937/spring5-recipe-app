package guru.springframework.recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes extends BaseDomain {
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;

}
