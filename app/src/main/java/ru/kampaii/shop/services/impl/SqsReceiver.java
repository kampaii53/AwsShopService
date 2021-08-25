package ru.kampaii.shop.services.impl;

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.messaging.handler.annotation.Header;

@Service
public class SqsReceiver {

    private static final Logger log = LoggerFactory.getLogger(SqsReceiver.class);

    @SqsListener(value = "test", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS )
    public void productCacheListener(final String message,  @Header("SenderId") String senderId) {
        log.info("got message for product {}", message);
    }
}
