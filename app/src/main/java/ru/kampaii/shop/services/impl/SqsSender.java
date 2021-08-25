package ru.kampaii.shop.services.impl;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.messaging.core.QueueMessageChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import ru.kampaii.shop.model.entities.ProductEntity;
import org.springframework.messaging.support.MessageBuilder;

@Service
public class SqsSender {

    private static final Logger log = LoggerFactory.getLogger(SqsSender.class);

    private final AmazonSQSAsync amazonSqs;

    @Autowired
    public SqsSender(final AmazonSQSAsync amazonSQSAsync) {
        this.amazonSqs = amazonSQSAsync;
    }

    public boolean send(final ProductEntity productEntity) {
        MessageChannel messageChannel = new QueueMessageChannel(amazonSqs, "test");

        Message<String> msg = MessageBuilder.withPayload(productEntity.toString())
                .setHeader("sender", "app1")
                .setHeaderIfAbsent("country", "AE")
                .build();

        return messageChannel.send(msg, 5000);
    }
}
