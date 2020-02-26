package mk.ukim.finki.emit.sports_shop.models;

import javax.persistence.*;

@Entity
@Table(name="manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) // autoincrement
    private Long id;

    @Column(name = "manu_name", length = 500)
    private String name;

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
}
