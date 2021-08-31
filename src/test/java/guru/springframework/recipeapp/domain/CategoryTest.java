package guru.springframework.recipeapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CategoryTest {
    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    void equality(){
        Category first = Category.builder().id(1L).build();
        Category second = Category.builder().id(2L).build();
        assertNotEquals(first, second);
    }
}