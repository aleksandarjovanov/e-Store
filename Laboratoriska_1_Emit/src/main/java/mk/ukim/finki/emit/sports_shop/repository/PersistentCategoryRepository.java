package mk.ukim.finki.emit.sports_shop.repository;

import mk.ukim.finki.emit.sports_shop.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersistentCategoryRepository extends Repository<Category,Long> {

    @Query("select c from Category c")
    List<Category> getAll();
}
