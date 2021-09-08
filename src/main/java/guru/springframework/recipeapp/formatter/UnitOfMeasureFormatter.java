package guru.springframework.recipeapp.formatter;

import guru.springframework.recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.recipeapp.services.UnitOfMeasureService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class UnitOfMeasureFormatter implements Formatter<UnitOfMeasureCommand> {
    private final UnitOfMeasureService unitOfMeasureService;

    public UnitOfMeasureFormatter(UnitOfMeasureService unitOfMeasureService) {
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    public UnitOfMeasureCommand parse(String id, Locale locale) throws ParseException {
        return unitOfMeasureService.getUnitOfMeasureCommandById(Long.valueOf(id));
    }

    @Override
    public String print(UnitOfMeasureCommand unitOfMeasureCommand, Locale locale) {
        return unitOfMeasureCommand != null ? unitOfMeasureCommand.getId().toString() : "";
    }
}
