package vic.test.aws.s3;

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

        // app init
        AmazonS3 s3 = new AmazonS3Client(new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return "AKIAIMJE6QOESP7IHWUQ";
            }

            @Override
            public String getAWSSecretKey() {
                return "ZfJu8/Sj1yoKbEcTYyT8qW+lzKrUIQI1pWAFC0B2";
            }
        });
        s3.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1));
        TransferManager transferManager = new TransferManager(s3);
        Stopwatch stopwatch = Stopwatch.createUnstarted();


        // app running
        String bucketName = "vic-test-bucket-" + UUID.randomUUID();

        System.out.println("Creating bucket " + bucketName);
        stopwatch.start();
        s3.createBucket(bucketName);
        System.out.println("Created bucket, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));


        System.out.println("\nList buckets");
        stopwatch.reset().start();
        for (Bucket bucket : s3.listBuckets()) {
            System.out.println(" - " + bucket.getName() + ", " + bucket.getCreationDate().toInstant().atZone(ZoneId.of("Pacific/Auckland")));
        }
        System.out.println("Listed buckets, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        stopwatch.reset();


        String key = "key-" + UUID.randomUUID().toString();
        System.out.println("\nPutting object " + key);
        stopwatch.reset().start();
        byte[] content = "hello world".getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(content.length);
        PutObjectResult putObjectResult = s3.putObject(bucketName, key, inputStream,  objectMetadata);
        System.out.println("Put object, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        StringBuilder putObjectResultString = new StringBuilder();
        putObjectResultString
                .append("\nversionId=").append(putObjectResult.getVersionId())
                .append("\ncontentMd5=").append(putObjectResult.getContentMd5())
                .append("\nETag=").append(putObjectResult.getETag())
                .append("\nexpirationTime=").append(putObjectResult.getExpirationTime())
                .append("\nexpirationTimeRuleId=").append(putObjectResult.getExpirationTimeRuleId())
                .append("\nSSEAlgorithm=").append(putObjectResult.getSSEAlgorithm())
                .append("\nSSECustomerAlgorithm=").append(putObjectResult.getSSECustomerAlgorithm())
                .append("\nSSECustomerKeyMd5=").append(putObjectResult.getSSECustomerKeyMd5());
        ObjectMetadata resultMetadata = putObjectResult.getMetadata();
        putObjectResultString.append("rawMetadata=").append(resultMetadata.getRawMetadata());
        System.out.println("resultString: " + putObjectResultString);


        System.out.println("\nGetting object 10 times" + key);
        stopwatch.reset().start();
        S3Object s3Object = s3.getObject(bucketName, key);
        System.out.println("Got object 10 times, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));

        if (false) {

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
        stopwatch.reset().start();
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


        // del all bucket
        for (Bucket bucket : s3.listBuckets()) {
            String name = bucket.getName();
            System.out.println("\nDeleting bucket " + name);
            stopwatch.reset().start();

            // del all content
            ObjectListing objectListing = s3.listObjects(name);
            while (true) {
                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    s3.deleteObject(name, objectSummary.getKey());
                }
                if (objectListing.isTruncated()) {
                    objectListing = s3.listNextBatchOfObjects(objectListing);
                } else {
                    break;
                }
            }
            // del version
            VersionListing versionListing = s3.listVersions(new ListVersionsRequest().withBucketName(name));
            for (S3VersionSummary versionSummary : versionListing.getVersionSummaries()) {
                s3.deleteVersion(name, versionSummary.getKey(), versionSummary.getVersionId());
            }
            // del bucket
            s3.deleteBucket(name);
            System.out.println("Deleted bucket, time cost(millis): " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }



        // app stop

    }

}
