package mk.ukim.finki.emit.sports_shop.service.impl;

import mk.ukim.finki.emit.sports_shop.models.Category;
import mk.ukim.finki.emit.sports_shop.repository.PersistentCategoryRepository;
import mk.ukim.finki.emit.sports_shop.service.CategoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("persist")
@Service
public class PersistentCategoryServiceImpl implements CategoryService {

    private PersistentCategoryRepository repo;

    public PersistentCategoryServiceImpl(PersistentCategoryRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Category> getAllCategories() {
        return repo.getAll();
    }
}
