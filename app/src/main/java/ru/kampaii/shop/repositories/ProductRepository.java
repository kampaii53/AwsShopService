package ru.kampaii.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kampaii.shop.model.entities.ProductEntity;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity,Long> {
}
