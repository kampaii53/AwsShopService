package ru.kampaii.orderservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsReader {

    private static final Logger log = LoggerFactory.getLogger(SqsReader.class);

    private final SqsSender sqsSender;

    public SqsReader(SqsSender sqsSender) {
        this.sqsSender = sqsSender;
    }

    @SqsListener(value = "cart-content", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void productCartListener(final String message) {
        log.info("got message for cart {}", message);
        sqsSender.send(message);
    }
}
