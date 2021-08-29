package ru.kampaii;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class AwsShopLambdaHandler implements RequestHandler<SQSEvent, Void> {
    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        AmazonSNS client = AmazonSNSClientBuilder.standard().build();
        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            //client.publish()
            client.publish("arn:aws:sns:us-east-1:378642390019:AwsShopTopic", msg.getBody());
        }
        return null;
    }
}
