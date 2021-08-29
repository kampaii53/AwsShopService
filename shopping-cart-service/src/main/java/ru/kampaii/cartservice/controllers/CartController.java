package ru.kampaii.cartservice.controllers;

import org.springframework.web.bind.annotation.*;
import ru.kampaii.cartservice.model.entities.CartEntity;
import ru.kampaii.cartservice.services.CartService;

@RestController
@RequestMapping(path = "/cart-service")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartEntity getCartById(@RequestParam(name = "id") Long id) {
        return cartService.findById(id);
    }

    @PutMapping("/default")
    public CartEntity putDefaultProductToCart(@RequestParam(name = "id") Long cartId) {
        return cartService.putDefaultProduct(cartId);
    }

    @PostMapping("/commit")
    public void postCart(@RequestParam(name = "id") Long cartId) {
        cartService.postCart(cartId);
    }

    @DeleteMapping
    public void deleteCartById(@RequestParam(name = "id") Long cartId) {
        cartService.deleteById(cartId);
    }
}
