package ru.kampaii.cartservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kampaii.cartservice.model.entities.CartEntity;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Long> {
}
