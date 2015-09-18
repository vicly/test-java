package vic.test.azurestorage.task;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

public class Main {

	public static void main(String[] args) throws Exception {
		final String account = "<yourAcct>";
		final String accessKey = "<yourKey>";
		final String storageConnectionString =
			"DefaultEndpointsProtocol=http;" +
			"AccountName=" + account + ";" +
			"AccountKey=" + accessKey;

		CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
		CloudTableClient tableClient = storageAccount.createCloudTableClient();

		final String tableName = "tasks";
		CloudTable table = new CloudTable(tableName, tableClient);
		table.createIfNotExists();

		listTables(tableClient);

/*		TaskEntity task;
		TableOperation insert;

		task = new TaskEntity("001", "buy milk", false);
		insert = TableOperation.insert(task);
		table.execute(insert);

		task = new TaskEntity("002", "return book", false);
		insert = TableOperation.insert(task);
		table.execute(insert);
*/
		String allTaskCondition = TableQuery.generateFilterCondition("PartitionKey", QueryComparisons.EQUAL, TaskEntity.PK);

		System.out.println(">> All Tasks");
		TableQuery<TaskEntity> queryAllTasks = TableQuery.from(TaskEntity.class).where(allTaskCondition);
		for (TaskEntity entity : table.execute(queryAllTasks)) {
			System.out.println(entity);
		}
		System.out.println("<<");

	}

	private static void listTables(CloudTableClient tableClient) {
		System.out.println(">> tables: ");
		for (String table : tableClient.listTables()) {
			System.out.println(table);
		}
		System.out.println("<<");
	}


}
