package vic.test.azurestorage;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTableClient;

public class Test {

	public static void main(String[] args) throws Exception {
		final String account = "<yourAcct>";
		final String accessKey = "<yourKey>";
		final String storageConnectionString =
			"DefaultEndpointsProtocol=http;" +
			"AccountName=" + account + ";" +
			"AccountKey=" + accessKey;

		CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
		CloudTableClient tableClient = storageAccount.createCloudTableClient();

		System.out.println("-----------Tables---------");
		for (String table : tableClient.listTables()) {
			System.out.println(table);
		}

//		CloudTable table = new CloudTable(tableName, tableClient);

	}

}
