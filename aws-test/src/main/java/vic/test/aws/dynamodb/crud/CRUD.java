package vic.test.aws.dynamodb.crud;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vic Liu
 */
public class CRUD {

    public static void main(String[] args) throws Exception {
        AmazonDynamoDBClient client = createLocalClient();
        DynamoDB dynamoDB = new DynamoDB(client);

        createTable(dynamoDB);
        listTables(dynamoDB);
    }

    static void createTable(DynamoDB dynamoDB) throws Exception {
        final String tableName = "Task";

        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("Id")
                .withAttributeType(ScalarAttributeType.N)
        );

        List<KeySchemaElement> keySchema = new ArrayList<>();
        keySchema.add(new KeySchemaElement()
                .withAttributeName("Id")
                .withKeyType(KeyType.HASH)
        );

        CreateTableRequest request = new CreateTableRequest()
                .withTableName(tableName)
                .withKeySchema(keySchema)
                .withAttributeDefinitions(attributeDefinitions)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));

        System.out.println("Issuing createTable - " + tableName);
        Table table = dynamoDB.createTable(request);

        System.out.println("Waiting for table creation..");
        table.waitForActive();
    }

    static void listTables(DynamoDB dynamoDB) {
        TableCollection<ListTablesResult> tables = dynamoDB.listTables();
        Iterator<Table> iterator = tables.iterator();

        System.out.println("Listing tables: ");
        while (iterator.hasNext()) {
            Table table = iterator.next();
            System.out.println(table.getTableName());
        }

    }

    static AmazonDynamoDBClient createLocalClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.withRegion(Regions.getCurrentRegion());
        client.withEndpoint("http://localhost:8000");
        return client;
    }

}
