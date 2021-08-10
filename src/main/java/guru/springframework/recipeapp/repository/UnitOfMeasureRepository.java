package guru.springframework.recipeapp.repository;

import guru.springframework.recipeapp.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, UUID> {
}
