package mk.ukim.finki.emit.sports_shop.service.impl;

import mk.ukim.finki.emit.sports_shop.models.Manufacturer;
import mk.ukim.finki.emit.sports_shop.repository.PersistentManufacturerRepository;
import mk.ukim.finki.emit.sports_shop.service.ManufacturerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("persist")
@Service
public class PersistentManufacturerServiceImpl implements ManufacturerService {

    private PersistentManufacturerRepository repo;

    public PersistentManufacturerServiceImpl(PersistentManufacturerRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return repo.getAll();
    }
}
