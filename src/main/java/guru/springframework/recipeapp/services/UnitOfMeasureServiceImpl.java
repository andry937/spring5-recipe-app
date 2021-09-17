package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipeapp.domain.UnitOfMeasure;
import guru.springframework.recipeapp.exceptions.NotFoundException;
import guru.springframework.recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureCommand = unitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasure> findAll() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        this.unitOfMeasureRepository.findAll().forEach(unitOfMeasures::add);
        return unitOfMeasures;
    }

    @Override
    public UnitOfMeasureCommand getUnitOfMeasureCommandById(Long id) {
        UnitOfMeasure unitOfMeasure = this.unitOfMeasureRepository.findById(id).orElseThrow(NotFoundException::new);
        return unitOfMeasureCommand.convert(unitOfMeasure);
    }

}
