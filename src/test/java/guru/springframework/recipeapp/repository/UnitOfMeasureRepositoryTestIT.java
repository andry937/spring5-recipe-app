package guru.springframework.recipeapp.repository;

import guru.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryTestIT {
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    void findByDescriptionTeaspoon() {
        Optional<UnitOfMeasure> value = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", value.orElseThrow(()->new RuntimeException("Category no found")).getDescription());
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> value = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup", value.orElseThrow(()->new RuntimeException("Category no found")).getDescription());
    }
}