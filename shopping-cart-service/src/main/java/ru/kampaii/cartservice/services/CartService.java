package ru.kampaii.cartservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kampaii.cartservice.model.entities.CartEntity;
import ru.kampaii.cartservice.model.entities.ProductEntity;
import ru.kampaii.cartservice.repositories.CartRepository;
import ru.kampaii.cartservice.repositories.ProductRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class CartService {

    private static final Logger log = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final SqsSender sqsSender;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, SqsSender sqsSender) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.sqsSender = sqsSender;
    }

    public CartEntity findById(Long id) {
        log.info("requested cart {}", id);
        return cartRepository.findById(id).orElseGet(
                () -> {
                    CartEntity cart = new CartEntity();
                    cart.setId(id);
                    cartRepository.save(cart);
                    log.info("saved new cart");
                    return cart;
                }
        );
    }

    public CartEntity putDefaultProduct(Long cartId) {
        log.info("putting default product to cart {}", cartId);
        CartEntity cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);

        ProductEntity product = new ProductEntity();
        product.setCart(cart);
        product.setType("default");
        productRepository.save(product);

        return cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
    }

    public void postCart(Long cartId){
        log.info("posting cart {}", cartId);
        CartEntity cart = cartRepository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        sqsSender.send(cart);
    }

    public void deleteById(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
