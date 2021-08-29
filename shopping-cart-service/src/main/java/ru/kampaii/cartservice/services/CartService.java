package ru.kampaii.cartservice.services;

import org.springframework.stereotype.Service;
import ru.kampaii.cartservice.model.entities.CartEntity;
import ru.kampaii.cartservice.model.entities.ProductEntity;
import ru.kampaii.cartservice.repositories.CartRepository;
import ru.kampaii.cartservice.repositories.ProductRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final SqsSender sqsSender;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, SqsSender sqsSender) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.sqsSender = sqsSender;
    }

    public CartEntity findById(Long id) {
        return cartRepository.findById(id).orElseGet(
                () -> {
                    CartEntity cart = new CartEntity();
                    cart.setId(id);
                    cartRepository.save(cart);
                    return cart;
                }
        );
    }

    public CartEntity putDefaultProduct(Long cartId) {
        CartEntity cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);

        ProductEntity product = new ProductEntity();
        product.setCart(cart);
        product.setType("default");
        productRepository.save(product);

        return cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
    }

    public void postCart(Long cartId){
        CartEntity cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        sqsSender.send(cart);
    }

    public void deleteById(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
