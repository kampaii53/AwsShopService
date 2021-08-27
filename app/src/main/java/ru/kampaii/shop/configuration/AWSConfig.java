package ru.kampaii.shop.configuration;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.context.config.annotation.EnableContextInstanceData;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableContextInstanceData
public class AWSConfig {
//
//    @Bean
//    public AwsCredentialsProvider awsCredentialsProvider() {
//        return DefaultCredentialsProvider.builder()
//                .profileName("kampaii")
//                .build();
//    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }
}
