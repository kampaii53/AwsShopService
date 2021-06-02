package ru.kampaii.shop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kampaii.shop.model.dto.ProductDto;

@RestController
@RequestMapping(path = "/product/2")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ProductDto getProduct() {
        log.debug("getProduct executes");
        return new ProductDto();
    }

    @PutMapping("/add")
    public void putProduct(ProductDto productDto) {
        log.debug("putProduct executes: {}", productDto);
    }

}
