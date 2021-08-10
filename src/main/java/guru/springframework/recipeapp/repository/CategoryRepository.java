package guru.springframework.recipeapp.repository;

import guru.springframework.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {
}
