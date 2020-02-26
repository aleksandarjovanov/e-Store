package mk.ukim.finki.emit.sports_shop.web.controllers.rest;

import mk.ukim.finki.emit.sports_shop.models.Product;
import mk.ukim.finki.emit.sports_shop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/product")
public class ProductREST {

    private ProductService productService;

    public ProductREST(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product productDetails(@PathVariable("id") Long pathId){
        return productService.getById(pathId);
    }

    @GetMapping("/category/{id}")
    public List<Product> productsInCategory(@PathVariable("id") Long pathId){
        return productService.getProductsByCategoryId(pathId);
    }

    @GetMapping("category/{categoryId}/manufacturer/{manufacturerId}")
    public List<Product> productsInCategoryAndManufacturer(@PathVariable("categoryId") Long catId, @PathVariable("manufacturerId") Long manId){
        return productService.getProductsByCategoryAndManufacturer(catId, manId);
    }

    @GetMapping("/category/{categoryId}/price")
    public Double PriceOfProductsInCategory(@PathVariable("categoryId") Long id){
        return productService.PriceOfProductsInCategory(id);
    }

    @PostMapping
    public Product createNewProduct(@RequestBody Product p){
        return productService.addNewProduct(p, p.getCategory().getId(), p.getManufacturer().getId());
    }

}
