package mk.ukim.finki.emit.sports_shop.service;

import mk.ukim.finki.emit.sports_shop.exception.ManufacturerNotFoundException;
import mk.ukim.finki.emit.sports_shop.exception.ProductNotFoundException;
import mk.ukim.finki.emit.sports_shop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> getPageProducts(Pageable page);

    Double PriceOfProductsInCategory(Long id);

    List<Product> getProductsByCategoryAndManufacturer(Long categoryId, Long manufacturerID);

    List<Product> getProductsByCategoryId(Long id);

    Product addNewProduct(String name, String description, String imgLink, Long categoryId, Long manufaturerId);

    Product addNewProduct(Product product, Long categoryId, Long manufaturerId);

    List<Product> getAllProducts();

    //Product update(Product device) throws ProductNotFoundException;

    void delete(Long productId);

    Product getById(Long productId);

}
