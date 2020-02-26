package mk.ukim.finki.emit.sports_shop.service.impl;

import mk.ukim.finki.emit.sports_shop.exception.CategoryNotFoundException;
import mk.ukim.finki.emit.sports_shop.exception.ManufacturerNotFoundException;
import mk.ukim.finki.emit.sports_shop.exception.ProductNotFoundException;
import mk.ukim.finki.emit.sports_shop.models.Category;
import mk.ukim.finki.emit.sports_shop.models.Manufacturer;
import mk.ukim.finki.emit.sports_shop.models.Product;
import mk.ukim.finki.emit.sports_shop.repository.ProductRepository;
import mk.ukim.finki.emit.sports_shop.service.CategoryService;
import mk.ukim.finki.emit.sports_shop.service.ManufacturerService;
import mk.ukim.finki.emit.sports_shop.service.ProductService;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Profile("in-memory")
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repo;
    private ManufacturerService manufacturerService;
    private CategoryService categoryService;

    public ProductServiceImpl(ProductRepository repo, ManufacturerService manufacturerService, CategoryService categoryService){
        this.repo = repo;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    @Override
    public Page<Product> getPageProducts(Pageable page) {
        return null;
    }

    @Override
    public Double PriceOfProductsInCategory(Long id) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryAndManufacturer(Long categoryId ,Long manufacturerId) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return null;
    }

    @Override
    public Product addNewProduct(String name, String description, String imgLink, Long categoryId, Long manufaturerId) throws ManufacturerNotFoundException, CategoryNotFoundException {

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
    public Product addNewProduct(Product newProduct, Long categoryId, Long manufaturerId) throws ManufacturerNotFoundException, CategoryNotFoundException {

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
    public void delete(Long productId){
        repo.delete(productId);
    }

    @Override
    public List<Product> getAllProducts(){
        return repo.findAllProducts();
    }

    @Override
    public Product getById(Long productId) throws ProductNotFoundException{
       Optional<Product> product = repo.findById(productId);
       return product.get();
    }
}
