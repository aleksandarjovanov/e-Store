package mk.ukim.finki.emit.sports_shop.repository;

import mk.ukim.finki.emit.sports_shop.models.Category;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryReposiroty {

    private List<Category> categories = null;
    private Long counter;

    @PostConstruct
    public void init(){

        counter = 1L;
        categories = new ArrayList<>();

        Category c1 = new Category();
        c1.setId(1l);
        c1.setName("Man");
        categories.add(c1);

        Category c2 = new Category();
        c2.setId(2l);
        c2.setName("Woman");
        categories.add(c2);

    }
    public Optional<Category> findById(Long categoryId){
        return categories.stream().filter(c -> c.getId().equals(categoryId)).findAny();
    }

    public List<Category> findAll() {
        return categories;
    }

  //  private Long getNextId() {
   //     return counter++;
    //}

}
