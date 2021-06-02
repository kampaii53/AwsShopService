package ru.kampaii.shop.services.impl;

import org.springframework.stereotype.Service;
import ru.kampaii.shop.model.entities.ProductEntity;
import ru.kampaii.shop.repositories.ProductRepository;
import ru.kampaii.shop.services.ProductService;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ProductEntity> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<ProductEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(String name) {
        repository.save(new ProductEntity(null,name));
    }
}
