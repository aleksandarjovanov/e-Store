package mk.ukim.finki.emit.sports_shop.web.controllers;

import mk.ukim.finki.emit.sports_shop.dto.ChargeRequest;
import mk.ukim.finki.emit.sports_shop.exception.CategoryNotFoundException;
import mk.ukim.finki.emit.sports_shop.exception.ManufacturerNotFoundException;
import mk.ukim.finki.emit.sports_shop.exception.ProductNotFoundException;
import mk.ukim.finki.emit.sports_shop.models.Category;
import mk.ukim.finki.emit.sports_shop.models.Manufacturer;
import mk.ukim.finki.emit.sports_shop.models.Product;
import mk.ukim.finki.emit.sports_shop.service.CategoryService;
import mk.ukim.finki.emit.sports_shop.service.ManufacturerService;
import mk.ukim.finki.emit.sports_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private ProductService productService;
    private ManufacturerService manufacturerService;
    private CategoryService categoryService;


    public ProductController(ProductService productService, ManufacturerService manufacturerService, CategoryService categoryService){
        this.productService = productService;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }

    @GetMapping("products")
    public String products(Model model){
        model.addAttribute("productList", productService.getAllProducts());
        return "products";
    }

    @GetMapping("products/{id}")
    public String productDetails(@PathVariable("id") Long pathId,
                            Model model) {

        Product product = productService.getById(pathId);
        model.addAttribute("product",product);
        model.addAttribute("name", product.getName());
        model.addAttribute("amount", product.getPrice().intValue()); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "productDetails";
    }

    @GetMapping("/products/add")
    public String addProductForm(Model model){
        model.addAttribute("manufacturers",manufacturerService.getAllManufacturers());
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product, Model model){

        productService.addNewProduct(product, product.getCategory().getId(), product.getManufacturer().getId());

        return "redirect:/products";
    }

    @DeleteMapping("/products/")
    public String deleteProduct(HttpServletRequest request){
        Long productId = Long.parseLong(request.getParameter("productId"));
        productService.delete(productId);
        return "redirect:/products";
    }

    @GetMapping("/products/page/{pageId}/{number}")
    public String productsByPage(Model model,@PathVariable("pageId") Integer pageId, @PathVariable("number") Integer number){
        model.addAttribute("productList", productService.getPageProducts(PageRequest.of(pageId,number)));
        return "products";
    }


}
