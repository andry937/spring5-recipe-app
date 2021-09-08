package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.CategoryCommand;
import guru.springframework.recipeapp.converters.CategoryToCategoryCommand;
import guru.springframework.recipeapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public Set<CategoryCommand> getAllCategories() {
        Set<CategoryCommand> categories = new HashSet<>();
        categoryRepository.findAll().forEach(category -> categories.add(categoryToCategoryCommand.convert(category)));
        return categories;
    }

    @Override
    public CategoryCommand getCategoryCommand(Long id) {
        return categoryToCategoryCommand.convert(categoryRepository.findById(id).orElse(null));
    }
}
