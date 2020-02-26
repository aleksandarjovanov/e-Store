package mk.ukim.finki.emit.sports_shop.repository;

import mk.ukim.finki.emit.sports_shop.models.Manufacturer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersistentManufacturerRepository extends Repository<Manufacturer,Long> {

    @Query("select m from Manufacturer m")
    List<Manufacturer> getAll();
}
