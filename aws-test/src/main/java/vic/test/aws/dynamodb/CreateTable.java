package vic.test.aws.dynamodb;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import java.util.Arrays;

/**
 * @author Vic Liu
 */
public class CreateTable {

    public static void main(String[] args) throws Exception {

        /*
         1. download DynamoDB local
            http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html

         2. run in memory mode
            java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -inMemory

            http://localhost:8000/shell/

         */

        AmazonDynamoDBClient client = new AmazonDynamoDBClient(new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return "xyz";
            }

            @Override
            public String getAWSSecretKey() {
                return "xyz";
            }
        }).withEndpoint("http://localhost:8000");

        DynamoDB dynamoDB = new DynamoDB(client);

        createTable(dynamoDB);
    }

    static void createTable(DynamoDB dynamo) throws Exception {
        String tableName = "Movies";

        TableCollection<ListTablesResult> tablesResultTableCollection = dynamo.listTables();
        tablesResultTableCollection.pages();
        for (Table ltr : dynamo.listTables()) {
            if (tableName.equals(ltr.getTableName())) {
                System.out.println("Table '" + tableName + "' already exists");
                return;
            }
        }

        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName)
                .withKeySchema(
                        new KeySchemaElement("year", KeyType.HASH),
                        new KeySchemaElement("title", KeyType.RANGE)
                )
                .withAttributeDefinitions(
                        new AttributeDefinition("year", ScalarAttributeType.N),
                        new AttributeDefinition("title", ScalarAttributeType.S)
                )
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(10L)
                        .withWriteCapacityUnits(10L))
                .withGeneralProgressListener(progressEvent -> System.out.println(">> " + progressEvent.getEventType()))

                ;

        System.out.println("Creating table '" + tableName + "'");
        Table table = dynamo.createTable(request);
        table.waitForActive();
        System.out.println("Created table '" + tableName + "', table status: " +
                table.getDescription().getTableStatus());
    }


}
