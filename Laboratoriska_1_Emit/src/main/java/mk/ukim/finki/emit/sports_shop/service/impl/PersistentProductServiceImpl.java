package mk.ukim.finki.emit.sports_shop.service.impl;

import mk.ukim.finki.emit.sports_shop.exception.CategoryNotFoundException;
import mk.ukim.finki.emit.sports_shop.exception.ManufacturerNotFoundException;
import mk.ukim.finki.emit.sports_shop.exception.ProductNotFoundException;
import mk.ukim.finki.emit.sports_shop.models.Category;
import mk.ukim.finki.emit.sports_shop.models.Manufacturer;
import mk.ukim.finki.emit.sports_shop.models.Product;
import mk.ukim.finki.emit.sports_shop.repository.PersistentProductRepository;
import mk.ukim.finki.emit.sports_shop.service.CategoryService;
import mk.ukim.finki.emit.sports_shop.service.ManufacturerService;
import mk.ukim.finki.emit.sports_shop.service.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Profile("persist")
@Service
public class PersistentProductServiceImpl implements ProductService {

    private PersistentProductRepository repo;
    private ManufacturerService manufacturerService;
    private CategoryService categoryService;

    public PersistentProductServiceImpl(PersistentProductRepository repo, ManufacturerService manufacturerService, CategoryService categoryService){
        this.repo = repo;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    @Override
    public Page<Product> getPageProducts(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public Double PriceOfProductsInCategory(Long categoryId) {
        Optional<Category> category = categoryService.getAllCategories().stream().filter(c -> c.getId().equals(categoryId)).findAny();
        if(!category.isPresent()){
            throw new CategoryNotFoundException();
        }

        return repo.calculatePrice(category.get());
    }

    @Override
    public List<Product> getProductsByCategoryAndManufacturer(Long categoryId, Long manufaturerId) {
        Optional<Category> category = categoryService.getAllCategories().stream().filter(c -> c.getId().equals(categoryId)).findAny();
        if(!category.isPresent()){
            throw new CategoryNotFoundException();
        }

        Optional<Manufacturer> manufacturer = manufacturerService.getAllManufacturers().stream().filter(m -> m.getId().equals(manufaturerId)).findAny();
        if(!manufacturer.isPresent()){
            throw new ManufacturerNotFoundException();
        }

        return repo.getByCategoryAndManufacturer(category.get(), manufacturer.get());

    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        Optional<Category> category = categoryService.getAllCategories().stream().filter(c -> c.getId().equals(categoryId)).findAny();
        if(!category.isPresent()){
            throw new CategoryNotFoundException();
        }

        return repo.getByCategory(category.get());
    }

    @Override
    public Product addNewProduct(String name, String description, String imgLink, Long categoryId, Long manufaturerId) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setImgLink(imgLink);

        Optional<Category> category = categoryService.getAllCategories().stream().filter(c -> c.getId().equals(categoryId)).findAny();
        if(!category.isPresent()){
            throw new CategoryNotFoundException();
        }
        newProduct.setCategory(category.get());


        Optional<Manufacturer> manufacturer = manufacturerService.getAllManufacturers().stream().filter(m -> m.getId().equals(manufaturerId)).findAny();
        if(!manufacturer.isPresent()){
            throw new ManufacturerNotFoundException();
        }
        newProduct.setManufacturer(manufacturer.get());

        repo.save(newProduct);

        return newProduct;
    }

    @Override
    public Product addNewProduct(Product product, Long categoryId, Long manufaturerId) {
        Optional<Category> category = categoryService.getAllCategories().stream().filter(c -> c.getId().equals(categoryId)).findAny();
        if(!category.isPresent()){
            throw new CategoryNotFoundException();
        }
        product.setCategory(category.get());


        Optional<Manufacturer> manufacturer = manufacturerService.getAllManufacturers().stream().filter(m -> m.getId().equals(manufaturerId)).findAny();
        if(!manufacturer.isPresent()){
            throw new ManufacturerNotFoundException();
        }

        product.setManufacturer(manufacturer.get());

        repo.save(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.getAll();
    }

    @Override
    public void delete(Long productId) {
        Optional<Product> d = repo.getAll().stream().filter(v -> v.getId().equals(productId)).findAny();
        if(!d.isPresent()){
            throw new ProductNotFoundException();
        }
        repo.delete(d.get());

    }

    @Override
    public Product getById(Long productId) {
        Optional<Product> product = repo.getById(productId);
        return product.get();
    }
}
