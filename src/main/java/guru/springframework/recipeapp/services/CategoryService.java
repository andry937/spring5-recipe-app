package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.domain.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getAllCategories();
}
