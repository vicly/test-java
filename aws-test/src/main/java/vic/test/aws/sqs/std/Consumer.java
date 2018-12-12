package vic.test.aws.sqs.std;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static vic.test.aws.sqs.std.Constants.QUEUE_NAME;

/**
 * @author Vic Liu
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        AmazonSQS sqs = Utils.createSqs();

        String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();

        while (true) {
            consumeQueue(sqs, queueUrl);
            Thread.sleep(1000);
        }

    }

    private static void consumeQueue(AmazonSQS sqs, String queueUrl) {
        System.out.println("\n-------------------------------");

        ReceiveMessageRequest request = new ReceiveMessageRequest(queueUrl);
        request.setMaxNumberOfMessages(10);
        request.setVisibilityTimeout(5);
        request.setAttributeNames(Sets.newHashSet("All"));

        ReceiveMessageResult result = sqs.receiveMessage(request);
        List<Message> messages = result.getMessages();
        if (messages.isEmpty()) {
            System.out.println("Processing messages: No data");
            return;
        }

        System.out.println("Processing messages: " + messages.size());
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        for (Message message : messages) {
            System.out.println("\n\tConsuming message: " + message.getMessageId());
            System.out.println("\t\tbody=" + message.getBody());
            System.out.println("\t\tattributes:");
            message.getAttributes().entrySet().stream().forEach((e) -> {
                System.out.println("\t\t\t" + e.getKey() + ": " + e.getValue());
            });
            stopwatch.reset().start();
            sqs.deleteMessage(queueUrl, message.getReceiptHandle());
            System.out.println("\tDeleted message: " + message.getMessageId()
                    + " +time cost " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }
}
