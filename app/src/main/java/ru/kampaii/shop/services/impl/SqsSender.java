package ru.kampaii.shop.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.kampaii.shop.model.entities.ProductEntity;

@Service
public class SqsSender {

    private static final Logger log = LoggerFactory.getLogger(SqsSender.class);

    public void send(ProductEntity productEntity) {
        log.info("sending productEntity to product-cache-details: {}", productEntity.toString());
    }
}
