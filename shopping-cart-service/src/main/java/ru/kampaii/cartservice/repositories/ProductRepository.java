package ru.kampaii.cartservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kampaii.cartservice.model.entities.ProductEntity;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
