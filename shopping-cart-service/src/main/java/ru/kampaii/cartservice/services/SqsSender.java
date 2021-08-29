package ru.kampaii.cartservice.services;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessageChannel;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.kampaii.cartservice.model.entities.CartEntity;
import ru.kampaii.cartservice.model.entities.ProductEntity;

import java.util.stream.Collectors;

@Service
public class SqsSender {

    private static final Logger log = LoggerFactory.getLogger(SqsSender.class);

    private final AmazonSQSAsync amazonSqs;
    private final QueueMessagingTemplate queueMessagingTemplate;
    private String queueUrl;

    @Autowired
    public SqsSender(final AmazonSQSAsync amazonSQSAsync) {
        this.amazonSqs = amazonSQSAsync;
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
        queueUrl = amazonSQSAsync.getQueueUrl("cart-content").getQueueUrl();
        log.info("got queueUrl: {}", queueUrl);
    }

    public void send(final CartEntity cart) {
        queueMessagingTemplate.convertAndSend(
                new QueueMessageChannel(amazonSqs, queueUrl),
                cart.getProducts().stream()
                        .map(ProductEntity::getType)
                        .collect(Collectors.joining(";"))
        );
    }
}
