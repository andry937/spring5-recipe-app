package guru.springframework.recipeapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Notes extends BaseDomain {
    @OneToOne
    @NotNull
    private Recipe recipe;
    @Lob
    @NotBlank
    private String recipeNotes;

    @Builder
    public Notes(Long id, Recipe recipe, String recipeNotes) {
        super(id);
        this.recipe = recipe;
        this.recipeNotes = recipeNotes;
    }
}
