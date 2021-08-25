package ru.kampaii.shop.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;
import ru.kampaii.shop.model.entities.ProductEntity;

@Service
public class SqsReceiver {

    private static final Logger log = LoggerFactory.getLogger(SqsReceiver.class);

    @SqsListener("product-cache-details")
    public void productCacheListener(ProductEntity product) {
        log.info("got message for product {}", product.toString());
    }
}
