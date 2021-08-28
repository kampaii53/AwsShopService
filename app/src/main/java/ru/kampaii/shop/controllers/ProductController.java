package ru.kampaii.shop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kampaii.shop.model.dto.ProductDto;
import ru.kampaii.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/product/2")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProducts() {
        log.info("getProducts executes");
        List<ProductDto> result = StreamSupport.stream(productService.getAll().spliterator(), false)
                .map(entity -> new ProductDto(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
        log.debug("getProducts got result");
        return result;
    }

    @PutMapping(
            value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String putProduct(@RequestBody ProductDto productDto) {
        log.info("putProduct executes: {}", productDto);
        return productService.add(productDto.getName()).getId().toString();
    }

}
