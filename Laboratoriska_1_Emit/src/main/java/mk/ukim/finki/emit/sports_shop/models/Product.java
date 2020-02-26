package mk.ukim.finki.emit.sports_shop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imgLink;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "man_id")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "device_accessory",
            joinColumns = @JoinColumn(name = "device_id"),
            inverseJoinColumns = @JoinColumn(name = "accessory"))
    private List<Accessory> accessories;

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

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getImgLink(){return imgLink;}
    public void setImgLink(String imgLink) { this.imgLink = imgLink; }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }
    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public void print() {
        System.out.println(category.getName());
        System.out.println(manufacturer.getName());
        System.out.println(id+"  "+name+"  "+price);
        System.out.println(description);
        System.out.println(imgLink);
    }
}
