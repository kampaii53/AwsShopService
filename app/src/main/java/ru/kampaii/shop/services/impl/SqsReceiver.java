package ru.kampaii.shop.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;
import org.springframework.messaging.handler.annotation.Header;

@Service
public class SqsReceiver {

    private static final Logger log = LoggerFactory.getLogger(SqsReceiver.class);

    @SqsListener(value = "product-cache-details", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void productCacheListener(final String message,  @Header("SenderId") String senderId) {
        log.info("got message for product {}", message);
    }
}
