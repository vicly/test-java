package vic.test.jdk.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {

	public static void main(String[] args) throws URISyntaxException, IOException {
		FileChannel in = Util.sampleData();

		System.out.println("file size (bytes): " + in.size());
		// in.force(true); // flush all unwritten data from channel to disk

		// allocate a buffer
		ByteBuffer buf = ByteBuffer.allocate(48); // 48 bytes
		int bytesRead = -1;
		int counter = 0;
		// step 1. read data from channel to buffer
		while (bytesRead != in.read(buf) && counter < 10) {
			counter++;
			// step 2. flip, make buffer ready to read: write mode to read mode
			buf.flip();
			// step 3. read
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get()); // 1 byte at a time
			}
			// step 4. call clear() or compact()
			buf.clear(); // make buffer ready for writing: read mode to write mode
			// compact(); // any unread data, you wanna read it later after another writing, use this.

			//buf.rewind(); // reset cursors, still in read mode, you can reread all data in the buffer
		}

		in.close();
	}

}
