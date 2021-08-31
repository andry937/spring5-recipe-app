package guru.springframework.recipeapp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Notes extends BaseDomain {
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;

    @Builder
    public Notes(Long id, Recipe recipe, String recipeNotes) {
        super(id);
        this.recipe = recipe;
        this.recipeNotes = recipeNotes;
    }
}
