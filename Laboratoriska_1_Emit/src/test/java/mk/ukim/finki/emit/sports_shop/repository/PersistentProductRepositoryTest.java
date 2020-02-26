package mk.ukim.finki.emit.sports_shop.repository;

import mk.ukim.finki.emit.sports_shop.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersistentProductRepositoryTest {

    @Autowired
    private PersistentProductRepository repo;

    @Before
    public void init() {

        Product p1 = new Product();
        p1.setName("AIR-MAX");
        repo.save(p1);

        Product p2 = new Product();
        p2.setName("Questand");
        repo.save(p2);
    }

    @Test
    public void getAll() {
        List<Product> productList = repo.getAll();
        Assert.assertEquals(2,productList.size());
    }

}