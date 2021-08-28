package ru.kampaii.shop.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.kampaii.shop.model.entities.ProductEntity;
import ru.kampaii.shop.repositories.ProductRepository;
import ru.kampaii.shop.services.ProductService;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository repository;
    private final SqsSender sqsSender;

    public ProductServiceImpl(ProductRepository repository, SqsSender sqsSender) {
        this.repository = repository;
        this.sqsSender = sqsSender;
    }

    @Override
    public Optional<ProductEntity> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Cacheable("ElastiCacheCluster")
    public Iterable<ProductEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public ProductEntity add(String name) {
        ProductEntity entity = repository.save(new ProductEntity(null, name));
        sqsSender.send(entity);
        return entity;
    }
}
