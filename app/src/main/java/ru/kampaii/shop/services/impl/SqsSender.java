package ru.kampaii.shop.services.impl;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessageChannel;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.kampaii.shop.model.entities.ProductEntity;

@Service
public class SqsSender {

    private static final Logger log = LoggerFactory.getLogger(SqsSender.class);

    private final AmazonSQSAsync amazonSqs;
    private final QueueMessagingTemplate queueMessagingTemplate;
    private String queueUrl;

    @Autowired
    public SqsSender(final AmazonSQSAsync amazonSQSAsync, QueueMessagingTemplate queueMessagingTemplate) {
        this.amazonSqs = amazonSQSAsync;
        queueUrl = amazonSQSAsync.getQueueUrl("product-cache-details").getQueueUrl();
        this.queueMessagingTemplate = queueMessagingTemplate;
        log.info("got queueUrl: {}", queueUrl);
    }

    public void send(final ProductEntity productEntity) {
        queueMessagingTemplate.convertAndSend(new QueueMessageChannel(amazonSqs,queueUrl), productEntity.toString());
    }
}
