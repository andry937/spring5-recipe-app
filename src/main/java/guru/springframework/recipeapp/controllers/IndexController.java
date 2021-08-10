package guru.springframework.recipeapp.controllers;

import guru.springframework.recipeapp.domain.Category;
import guru.springframework.recipeapp.domain.UnitOfMeasure;
import guru.springframework.recipeapp.repository.CategoryRepository;
import guru.springframework.recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"/", "", "/index"})
    public String getIndexPage() {
        Optional<Category> category = categoryRepository.findByDescription("Fast Food");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");
        System.out.println("Category id is :"+category.get().getId());
        System.out.println("Unit of measure id is :"+unitOfMeasure.get().getId());
        return "index";
    }
}
