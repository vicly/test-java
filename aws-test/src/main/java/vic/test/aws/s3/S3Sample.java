package vic.test.aws.s3;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.google.common.base.Stopwatch;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

import java.io.ByteArrayInputStream;
import java.time.ZoneId;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Vic Liu
 */
public class S3Sample {

    public static void main(String[] args) throws Exception {

        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider("learning");

        // app init
        AmazonS3 s3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTHEAST_2)
                .withCredentials(awsCredentialsProvider).build();

        String bucketName = "vic-test-bucket-" + UUID.randomUUID();

        createBucket(s3, bucketName);

        separator();

        listAllBuckets(s3);


        String objectKey = "key-" + UUID.randomUUID().toString();
        separator();
        putObject(s3, bucketName, objectKey);
        separator();
        getObject(s3, bucketName, objectKey);

        if (false) {
            separator();
            uploadBigFile(s3, bucketName);
        }

        separator();
        deleteBucket(s3, bucketName);

    }

    private static void getObject(AmazonS3 s3, String bucketName, String key) {
        System.out.println("\nGetting object 10 times" + key);
        Stopwatch stopwatch = Stopwatch.createStarted();
        S3Object s3Object = s3.getObject(bucketName, key);
        System.out.println("Got object 10 times, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void putObject(AmazonS3 s3, String bucketName, String key) {
        System.out.println("Putting object: " + key);
        Stopwatch stopwatch = Stopwatch.createStarted();

        byte[] content = "hello world".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(content.length);

        PutObjectResult putObjectResult = s3.putObject(bucketName, key, inputStream,  objectMetadata);
        System.out.println("Put object, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));

        StringBuilder putObjectResultString = new StringBuilder();
        putObjectResultString
                .append("\n\tversionId=").append(putObjectResult.getVersionId())
                .append("\n\tcontentMd5=").append(putObjectResult.getContentMd5())
                .append("\n\tETag=").append(putObjectResult.getETag())
                .append("\n\texpirationTime=").append(putObjectResult.getExpirationTime())
                .append("\n\texpirationTimeRuleId=").append(putObjectResult.getExpirationTimeRuleId())
                .append("\n\tSSEAlgorithm=").append(putObjectResult.getSSEAlgorithm())
                .append("\n\tSSECustomerAlgorithm=").append(putObjectResult.getSSECustomerAlgorithm())
                .append("\n\tSSECustomerKeyMd5=").append(putObjectResult.getSSECustomerKeyMd5());
        ObjectMetadata resultMetadata = putObjectResult.getMetadata();
        putObjectResultString.append("\n\trawMetadata=").append(resultMetadata.getRawMetadata());
        System.out.println("resultString: " + putObjectResultString);
    }

    private static void separator() {
        System.out.println("\n-------------------------------------------------\n");
    }

    private static void listAllBuckets(AmazonS3 s3) {
        System.out.println("List buckets");
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (Bucket bucket : s3.listBuckets()) {
            System.out.println(" - " + bucket.getName() + ", " + bucket.getCreationDate().toInstant().atZone(ZoneId.of("Pacific/Auckland")));
        }
        System.out.println("Listed buckets, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void uploadBigFile(AmazonS3 s3, String bucketName) throws InterruptedException {
        System.out.println("\nUploading big file");

        final int FOUR_MEGABYTES = 4 * 1024 * 1024;
        byte[] payload = new byte[FOUR_MEGABYTES];
        for (int i = 0; i < FOUR_MEGABYTES; i++) {
            payload[i] = (byte) (i % 256);
        }

        ByteArrayInputStream f1 = new ByteArrayInputStream(payload);
        ObjectMetadata om1 = new ObjectMetadata();
        om1.setContentLength(FOUR_MEGABYTES);
        ByteArrayInputStream f2 = new ByteArrayInputStream(payload);
        ObjectMetadata om2 = new ObjectMetadata();
        om2.setContentLength(FOUR_MEGABYTES);
        ByteArrayInputStream f3 = new ByteArrayInputStream(payload);
        ObjectMetadata om3 = new ObjectMetadata();
        om3.setContentLength(FOUR_MEGABYTES);
        ByteArrayInputStream f4 = new ByteArrayInputStream(payload);
        ObjectMetadata om4 = new ObjectMetadata();
        om4.setContentLength(FOUR_MEGABYTES);
        ByteArrayInputStream f5 = new ByteArrayInputStream(payload);
        ObjectMetadata om5 = new ObjectMetadata();
        om5.setContentLength(FOUR_MEGABYTES);

        Stopwatch stopwatch = Stopwatch.createStarted();
        TransferManager transferManager = new TransferManager(s3);
        Upload up1 = transferManager.upload(bucketName, "big-file-1", f1, om1);
        Upload up2 = transferManager.upload(bucketName, "big-file-2",  f2, om2);
        Upload up3 = transferManager.upload(bucketName, "big-file-3",  f3, om3);
        Upload up4 = transferManager.upload(bucketName, "big-file-4",  f4, om4);
        Upload up5 = transferManager.upload(bucketName, "big-file-5",  f5, om5);
        up1.waitForCompletion();
        System.out.println("Uploaded bigfile 1, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        up2.waitForCompletion();
        System.out.println("Uploaded bigfile 2, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        up3.waitForCompletion();
        System.out.println("Uploaded bigfile 3, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        up4.waitForCompletion();
        System.out.println("Uploaded bigfile 4, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        up5.waitForCompletion();
        System.out.println("Uploaded bigfile 5, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void createBucket(AmazonS3 s3, String bucketName) {
        System.out.println("Creating bucket " + bucketName);
        Stopwatch stopwatch = Stopwatch.createStarted();
        s3.createBucket(bucketName);
        System.out.println("Created bucket, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    //
    // DANGEROUS !!
    //
    private static void deleteAllBuckets(AmazonS3 s3) {
        System.out.println("!!! Deleting All Buckets");
        for (Bucket bucket : s3.listBuckets()) {
            deleteBucket(s3, bucket.getName());
        }
    }

    private static void deleteBucket(AmazonS3 s3, String bucketName) {
        System.out.format("[%s] Deleting bucket\n", bucketName);

        Stopwatch stopwatch = Stopwatch.createStarted();

        // del all content
        ObjectListing objectListing = s3.listObjects(bucketName);
        while (true) {
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                System.out.format("[%s] Deleting object: %s\n", bucketName, objectSummary.getKey());
                s3.deleteObject(bucketName, objectSummary.getKey());
            }
            if (objectListing.isTruncated()) {
                objectListing = s3.listNextBatchOfObjects(objectListing);
            } else {
                break;
            }
        }
        // del version
        VersionListing versionListing = s3.listVersions(new ListVersionsRequest().withBucketName(bucketName));
        for (S3VersionSummary versionSummary : versionListing.getVersionSummaries()) {
            System.out.format("[%s] Deleting version: versionId=%s\n", bucketName, versionSummary.getVersionId());
            s3.deleteVersion(bucketName, versionSummary.getKey(), versionSummary.getVersionId());
        }

        // del bucket
        s3.deleteBucket(bucketName);
        System.out.format("[%s] Deleted bucket, time cost(millis): %s", bucketName, stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

}
