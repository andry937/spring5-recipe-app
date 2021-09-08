package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.commands.CategoryCommand;

import java.util.Set;

public interface CategoryService {
    Set<CategoryCommand> getAllCategories();
}
