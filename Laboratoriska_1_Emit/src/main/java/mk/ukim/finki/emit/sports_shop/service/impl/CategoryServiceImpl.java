package mk.ukim.finki.emit.sports_shop.service.impl;

import mk.ukim.finki.emit.sports_shop.models.Category;
import mk.ukim.finki.emit.sports_shop.repository.CategoryReposiroty;
import mk.ukim.finki.emit.sports_shop.service.CategoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("in-memory")
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryReposiroty repo;

    public CategoryServiceImpl(CategoryReposiroty repo) {
        this.repo = repo;
    }

    @Override
    public List<Category> getAllCategories(){
        return repo.findAll();
    }
}
