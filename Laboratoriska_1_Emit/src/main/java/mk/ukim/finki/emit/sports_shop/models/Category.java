package mk.ukim.finki.emit.sports_shop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*@OneToMany(mappedBy = "manufacturer",
            fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Product> products;*/

    public Long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    /*public List<Product> getproducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }*/
}
