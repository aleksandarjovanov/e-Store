package mk.ukim.finki.emit.sports_shop.repository;

import mk.ukim.finki.emit.sports_shop.models.Category;
import mk.ukim.finki.emit.sports_shop.models.Manufacturer;
import mk.ukim.finki.emit.sports_shop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PersistentProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findAll(Pageable pageable);

    @Query("select p from Product p")
    List<Product> getAll();

    Product save(Product p);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="delete from Product p where p=:p")
    void delete(@Param("p") Product p);

    @Query("select p from Product p where p.id=:id")
    Optional<Product> getById(@Param("id") Long id); // @Param(id) i :id treba da se isto imenuvani

    @Query("select p from Product p where p.category=:category")  // nema potreba od cat.id, snaga se so cel objekt
    List<Product> getByCategory(@Param("category") Category category);

    @Query("select p from Product p where p.category=:category and p.manufacturer=:manufacturer")
    List<Product> getByCategoryAndManufacturer(@Param("category") Category category, @Param("manufacturer") Manufacturer manufacturer);

    @Query(value="select sum(p.price) from Product p where p.category=:category")
    Double calculatePrice(@Param("category") Category category);

}
