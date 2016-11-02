package vic.test.aws.dynamodb.crud;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;
import java.util.UUID;

/**
 * @author Vic Liu
 */
@DynamoDBTable(tableName = "Task")
public class Task {

    public enum Status {
        NEW, ASSIGNED, COMPLETE
    }

    private UUID id;
    private UUID owner;
    private String title;
    private Date createdAt;
    private Status status = Status.NEW;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @DynamoDBHashKey(attributeName = "OwnerId")
    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBRangeKey(attributeName = "CreatedAt")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
