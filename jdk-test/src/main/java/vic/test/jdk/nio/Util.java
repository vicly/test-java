package vic.test.jdk.nio;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Util {

	public static FileChannel sampleData() throws URISyntaxException, IOException {
		URL url = ReadFile.class.getResource("data.txt");
		URI uri = url.toURI();
		File file = new File(uri);
		Path path = file.toPath();
		FileChannel in = FileChannel.open(path, StandardOpenOption.READ);
		return in;
	}

}
