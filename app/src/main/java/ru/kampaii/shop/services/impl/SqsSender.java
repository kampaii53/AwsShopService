package ru.kampaii.shop.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.kampaii.shop.model.entities.ProductEntity;

@Service
public class SqsSender {

    private static final Logger log = LoggerFactory.getLogger(SqsSender.class);
    private final QueueMessagingTemplate template;

    public SqsSender(QueueMessagingTemplate template) {
        this.template = template;
    }

    public void send(ProductEntity productEntity) {
        log.info("sending productEntity to product-cache-details: {}", productEntity.toString());
        template.convertAndSend("product-cache-details", productEntity);
    }
}