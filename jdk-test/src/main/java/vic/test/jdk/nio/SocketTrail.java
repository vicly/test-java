package vic.test.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class SocketTrail {

	public static void main(String[] args) throws IOException {
		boolean blockMode = false;

		MySocketServer server = new MySocketServer(blockMode);
		Thread serverThread = new Thread(server);
		serverThread.start();

		for (int i = 0; i < 3; i++) {
			System.out.println("--------------<< " + i + " >>------------------");
			connectAndRead();
			System.out.println();
		}

		server.stop = true;
		if (blockMode) {
			connectAndRead(); // trigger server stop
		}
	}

	static void connectAndRead() throws IOException {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		SocketChannel channel = SocketChannel.open();
		channel.connect(new InetSocketAddress("localhost", 9999));

		ByteBuffer buf = ByteBuffer.allocate(1024);
		int bytesRead = channel.read(buf);
		System.out.println(">> bytes read: " + bytesRead);

		System.out.print(">> contents: ");
		buf.flip();
		while (buf.hasRemaining()) {
			System.out.print((char) buf.get());
		}
		buf.clear();

		channel.close();
	}

}

class MySocketServer implements Runnable {

	boolean stop = false;
	ServerSocketChannel ssc;

	MySocketServer(boolean blockMode) throws IOException {
		ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(9999));
		ssc.configureBlocking(blockMode);
		System.out.println("Server is listening on port 9999...");
	}


	@Override
	public void run() {
		while (! stop) {
			try {
				SocketChannel sc = ssc.accept(); // block here

				if (sc != null) {
					String msg = "hello, the time is " + (new Date());
					ByteBuffer hello = ByteBuffer.allocate(1000);
//					hello.clear();
					hello.put(msg.getBytes());
					hello.flip();

					sc.write(hello);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			ssc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
