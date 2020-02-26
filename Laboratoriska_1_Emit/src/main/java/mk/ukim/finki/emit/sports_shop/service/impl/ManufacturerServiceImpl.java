package mk.ukim.finki.emit.sports_shop.service.impl;

import mk.ukim.finki.emit.sports_shop.models.Manufacturer;
import mk.ukim.finki.emit.sports_shop.repository.ManufacturerRepository;
import mk.ukim.finki.emit.sports_shop.service.ManufacturerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("in-memory")
@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerRepository repo;

    public ManufacturerServiceImpl(ManufacturerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Manufacturer> getAllManufacturers(){
        return repo.findAll();
    }
}
