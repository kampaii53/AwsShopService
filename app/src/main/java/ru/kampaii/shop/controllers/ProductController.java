package ru.kampaii.shop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.kampaii.shop.model.dto.ProductDto;
import ru.kampaii.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/product/2")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        log.debug("getProducts executes");
        return StreamSupport.stream(productService.getAll().spliterator(), false)
                .map(entity -> new ProductDto(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
    }

    @PutMapping("/add")
    public void putProduct(@RequestBody ProductDto productDto) {
        log.debug("putProduct executes: {}", productDto);
        productService.add(productDto.getName());
    }

}
