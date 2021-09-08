package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.recipeapp.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasure> findAll();
    UnitOfMeasureCommand getUnitOfMeasureCommandById(Long id);
}
