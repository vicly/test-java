package vic.test.jdk.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatteringRead {

	public static void main(String[] args) throws URISyntaxException, IOException {

		ByteBuffer buf1 = ByteBuffer.allocate(7);
		ByteBuffer buf2 = ByteBuffer.allocate(7);

		ByteBuffer[] buffers = { buf1, buf2 };

		FileChannel channel = Util.sampleData();
		channel.read(buffers); //  when first is full, write to next buf
		//channel.close();

		System.out.println("--------- buf 1 ----------");
		buf1.flip();
		while (buf1.hasRemaining()) {
			System.out.print((char) buf1.get());
		}
		System.out.println();

		System.out.println("--------- buf 2 ----------");
		buf2.flip();
		while (buf2.hasRemaining()) {
			System.out.print((char) buf2.get());
		}

	}

}
