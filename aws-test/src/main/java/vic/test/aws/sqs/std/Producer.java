package vic.test.aws.sqs.std;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static vic.test.aws.sqs.std.Constants.QUEUE_NAME;

/**
 * @author Vic Liu
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider("learning");

        AmazonSQS sqs = AmazonSQSClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(Regions.AP_SOUTHEAST_2)
                .build();

        String queueUrl = createOrGetQueue(sqs, QUEUE_NAME);

        while (true) {
            sendSingleRandomMessage(sqs, queueUrl);
            sendMultipleRandomMessage(sqs, queueUrl);
            Thread.sleep(1000);
        }

    }

    private static String createOrGetQueue(AmazonSQS sqs, String queueName) {
        try {
            String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
            System.out.println("Queue already exists: " + queueUrl);

            enableLongPooling(sqs, queueUrl);

            return queueUrl;
        } catch (QueueDoesNotExistException notExist) {
            System.out.println("Creating queue " + queueName);
            Stopwatch stopwatch = Stopwatch.createStarted();

/*
            CreateQueueRequest request = new CreateQueueRequest(queueName)
                    // delay delivery, by default 0
                    .addAttributesEntry("DelaySeconds", "60")
                    // in seconds, default 345600(4 days)
                    .addAttributesEntry("MessageRetentionPeriod", "86400");
            sqs.createQueue(request);
*/

/*
            // Enable long polling when creating a queue
            CreateQueueRequest request = new CreateQueueRequest(queueName)
                    .addAttributesEntry("ReceiveMessageWaitTimeSeconds", "20"); // *0 to 20
            sqs.createQueue(request);
*/

            // standard queue
            CreateQueueResult createQueueResult = sqs.createQueue(queueName);
            String queueUrl = createQueueResult.getQueueUrl();
            System.out.println("Created queue: " + queueUrl + ", time cost: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
            return queueUrl;
        }
    }

    private static void enableLongPooling(AmazonSQS sqs, String queueUrl) {
        SetQueueAttributesRequest request = new SetQueueAttributesRequest()
                .withQueueUrl(queueUrl)
                .addAttributesEntry("ReceiveMessageWaitTimeSeconds", "20");
        sqs.setQueueAttributes(request);
    }

    private static void sendSingleRandomMessage(AmazonSQS sqs, String queueUrl) {

        SendMessageRequest request = new SendMessageRequest()
                .withDelaySeconds(5)
                .withQueueUrl(queueUrl)
                .withMessageBody(randomMessageBody())
                .withMessageAttributes(randomMessageAttributeValueMap());

        Stopwatch stopwatch = Stopwatch.createStarted();
        SendMessageResult result = sqs.sendMessage(request);
        System.out.format("Sent message: id=%s, time cost=%s\n", result.getMessageId(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void sendMultipleRandomMessage(AmazonSQS sqs, String queueUrl) {
        SendMessageBatchRequest request = new SendMessageBatchRequest()
                .withQueueUrl(queueUrl)
                .withEntries(
                        new SendMessageBatchRequestEntry()
                                .withId("msg_1")
                                .withMessageBody(randomMessageBody())
                                .withMessageAttributes(randomMessageAttributeValueMap()),
                        new SendMessageBatchRequestEntry()
                                .withId("msg_2")
                                .withMessageBody(randomMessageBody())
                                .withMessageAttributes(randomMessageAttributeValueMap()),
                        new SendMessageBatchRequestEntry()
                                .withId("msg_3")
                                .withMessageBody(randomMessageBody())
                                .withMessageAttributes(randomMessageAttributeValueMap()),
                        new SendMessageBatchRequestEntry()
                                .withId("msg_4")
                                .withMessageBody(randomMessageBody())
                                .withMessageAttributes(randomMessageAttributeValueMap()),
                        new SendMessageBatchRequestEntry()
                                .withId("msg_5")
                                .withMessageBody(randomMessageBody())
                                .withMessageAttributes(randomMessageAttributeValueMap()),
                        new SendMessageBatchRequestEntry()
                                .withId("msg_6")
                                .withMessageBody(randomMessageBody())
                                .withMessageAttributes(randomMessageAttributeValueMap()),
                        new SendMessageBatchRequestEntry()
                                .withId("msg_7")
                                .withMessageBody(randomMessageBody())
                                .withMessageAttributes(randomMessageAttributeValueMap())
                );

        Stopwatch stopwatch = Stopwatch.createStarted();
        SendMessageBatchResult result = sqs.sendMessageBatch(request);
        System.out.format("Batch sent message: failed=%s, successful=%s, time cost=%s\n",
                result.getFailed().size(),
                result.getSuccessful().size(),
                stopwatch.elapsed(TimeUnit.MILLISECONDS));

    }

    private static Map<String, MessageAttributeValue> randomMessageAttributeValueMap() {
        Map<String, MessageAttributeValue> messageAttributes = Maps.newHashMap();
        messageAttributes.put("sender", new MessageAttributeValue()
                .withDataType("String")
                .withStringValue("service_" + RandomStringUtils.randomAlphanumeric(5)));
        return messageAttributes;
    }

    private static String randomMessageBody() {
        return "message_" + RandomStringUtils.randomAlphabetic(20);
    }
}
