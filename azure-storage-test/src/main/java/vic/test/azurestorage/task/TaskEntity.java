package vic.test.azurestorage.task;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class TaskEntity extends TableServiceEntity {

	public static final String PK = "azure.entity.task";

	private String description;
	private boolean completed;

	public TaskEntity() {
		// for azure
	}

	public TaskEntity(String id, String description, boolean completed) {
		super(PK, id); // row key is id
		this.description = description;
		this.completed = completed;
	}

	public String getId() {
		return getRowKey();
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return this.completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String toString() {
		return "Task: id=" + getPartitionKey() + ", description=" + description + ", completed=" + completed;
	}

}
