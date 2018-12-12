package vic.test.aws.sqs.std;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ListQueuesResult;

/**
 * @author Vic Liu
 */
public class Utils {

    public static AmazonSQS createSqs() {
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider("learning");
        return AmazonSQSClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(Regions.AP_SOUTHEAST_2)
                .build();
    }

    public static String getQueueUrl(AmazonSQS sqs, String queueName) {
        ListQueuesResult result = sqs.listQueues(queueName);
        if (!result.getQueueUrls().isEmpty()) {
            return result.getQueueUrls().get(0);
        } else {
            throw new IllegalStateException("Queue does not exist: " + queueName);
        }
    }
}
