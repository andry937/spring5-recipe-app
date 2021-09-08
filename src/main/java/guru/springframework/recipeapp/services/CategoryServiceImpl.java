package guru.springframework.recipeapp.services;

import guru.springframework.recipeapp.domain.Category;
import guru.springframework.recipeapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getAllCategories() {
        Set<Category> categories = new HashSet<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }
}
