package ru.kampaii.shop.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.kampaii.shop.model.entities.ProductEntity;
import ru.kampaii.shop.model.entities.dynamo.ProductDynamoEntity;
import ru.kampaii.shop.repositories.ProductRepository;
import ru.kampaii.shop.repositories.dynamo.ProductDynamoRepository;
import ru.kampaii.shop.services.ProductService;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "product-cache")
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository repository;
    private final ProductDynamoRepository productDynamoRepository;

    public ProductServiceImpl(ProductRepository repository, ProductDynamoRepository productDynamoRepository) {
        this.repository = repository;
        this.productDynamoRepository = productDynamoRepository;
    }

    @Override
    @Cacheable
    public Optional<ProductEntity> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Cacheable
    public Iterable<ProductEntity> getAll() {
        return productDynamoRepository.findAll().stream().map(
                productDynamoEntity -> new ProductEntity(productDynamoEntity.getId(), productDynamoEntity.getName())
        ).collect(Collectors.toList());
    }

    @Override
    public ProductEntity add(String name) {
        ProductEntity entity = repository.save(new ProductEntity(null, name));
        ProductDynamoEntity productDynamoEntity = new ProductDynamoEntity();
        productDynamoEntity.setId(entity.getId());
        productDynamoEntity.setName(name);
        productDynamoRepository.save(
                productDynamoEntity
        );
        return entity;
    }
}
