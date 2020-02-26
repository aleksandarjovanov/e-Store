package mk.ukim.finki.emit.sports_shop.repository;

import mk.ukim.finki.emit.sports_shop.models.Category;
import mk.ukim.finki.emit.sports_shop.models.Manufacturer;
import mk.ukim.finki.emit.sports_shop.models.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private Long counter;
    private List<Product> productList = null;
    private ManufacturerRepository manufacturerRepository = null;
    private CategoryReposiroty categoryRepository = null;

    public ProductRepository(ManufacturerRepository manufacturerRepository, CategoryReposiroty categoryRepository){
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init(){
        counter = 0l;
        productList = new ArrayList<>();

        Product p1 = new Product();
        p1.setId(getNextId());
        p1.setManufacturer(manufacturerRepository.findById(2l).get());
        p1.setCategory(categoryRepository.findById(1l).get());
        p1.setName("AIR MAX COMMAND ");
        p1.setDescription("Airmax is the most popular nike product");
        p1.setImgLink("https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/tdwkq8lazt2a1yuv5hmq/air-max-90-leather-shoe-xqTPGEVE.jpg");
        productList.add(p1);


        Product p2 = new Product();
        p2.setId(getNextId());
        p2.setManufacturer(manufacturerRepository.findById(1l).get());
        p2.setCategory(categoryRepository.findById(2l).get());
        p2.setName("QUESTAR TND");
        p2.setDescription("QUESTAR TND is the most popular adidas product");
        p2.setImgLink("https://ae01.alicdn.com/kf/HTB1znr1a5QnBKNjSZFmq6AApVXaT/Original-New-Arrival-2018-Adidas-QUESTAR-TND-Men-s-Running-Shoes-Sneakers.jpg_640x640.jpg");
        productList.add(p2);

        Product p3 = new Product();
        p3.setId(getNextId());
        p3.setManufacturer(manufacturerRepository.findById(2l).get());
        p3.setCategory(categoryRepository.findById(1l).get());
        p3.setName("LEBRON XVI ");
        p3.setDescription(" LEBRON XVI is an evolution in James’ signature line");
        p3.setImgLink("https://static.shiekh.com/media/catalog/product/cache/image/1200x1200/e9c3970ab036de70892d86c6d221abfe/e/8/e8172983c6bffde0295ddeba596100f1.jpg");
        productList.add(p3);
    }

    public List<Product> findAllProducts() {
        return productList;
    }

    public Product save(Product product){
        product.setId(getNextId());
        productList.add(product);
        return product;
    }

    public void delete(Long productId){
        productList.removeIf(v->{
            return v.getId().equals(productId);
        });
    }

    public Optional<Product> findById(Long productId){
        return productList.stream().filter(v-> v.getId().equals(productId)).findAny();
    }

    private Long getNextId() {
        return counter++;
    }
}
