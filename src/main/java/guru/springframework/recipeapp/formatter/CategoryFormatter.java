package guru.springframework.recipeapp.formatter;

import guru.springframework.recipeapp.commands.CategoryCommand;
import guru.springframework.recipeapp.services.CategoryService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class CategoryFormatter implements Formatter<CategoryCommand> {
    private final CategoryService categoryService;

    public CategoryFormatter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryCommand parse(String id, Locale locale) {
        return categoryService.getCategoryCommand(Long.valueOf(id));
    }

    @Override
    public String print(CategoryCommand categoryCommand, Locale locale) {
        return categoryCommand != null ? categoryCommand.getId().toString() : "";
    }
}
