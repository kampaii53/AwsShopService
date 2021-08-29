package ru.kampaii;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class AwsShopLambdaHandler implements RequestHandler<SQSEvent, Void> {
    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            logger.log(msg.getBody());
        }
        return null;
    }
}
