package ru.kampaii.shop.services;

import ru.kampaii.shop.model.entities.ProductEntity;

import java.util.Optional;

public interface ProductService {

    Optional<ProductEntity> getById(Long id);

    Iterable<ProductEntity> getAll();

    ProductEntity add(String name);
}
