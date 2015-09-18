package vic.test.jdk.nio;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UdpTrail {

	public static void main(String[] args) throws IOException {

		int clientPort = 55892;
		int serverPort = 9999;
		InetSocketAddress clientAddress = new InetSocketAddress("localhost", clientPort);
		InetSocketAddress serverAddress = new InetSocketAddress("localhost", serverPort);

		UdpTimeServer server = new UdpTimeServer(serverAddress);
		Thread t = new Thread(server);
		t.start();

		UdpTimeClient receiver = new UdpTimeClient(clientAddress, serverAddress);
		Thread t2 = new Thread(receiver);
		t2.start();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}

		server.stop = true;
		receiver.stop = true;
	}

}

class UdpTimeClient implements Runnable {
	boolean stop = false;
	InetSocketAddress clientAddress;
	InetSocketAddress serverAddress;

	UdpTimeClient(InetSocketAddress clientAddress, InetSocketAddress serverAddress) throws IOException {
		this.clientAddress = clientAddress;
		this.serverAddress = serverAddress;
	}

	@Override
	public void run() {
		while (! stop) {
			try {
				execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	void execute() throws IOException {
		// client channel, bind to any available port
	    DatagramChannel channel = DatagramChannel.open();
	    DatagramSocket socket = channel.socket();
	    socket.setSoTimeout(5000);
//	    socket.bind(new InetSocketAddress(0)); // any available port
	    socket.bind(clientAddress);

	    // send request to server
		ByteBuffer buf = ByteBuffer.allocate(100);
		buf.clear();
		buf.put((byte) 65); // at least 1 byte, no matter what it's
		buf.flip();
		channel.send(buf, serverAddress);

		// get time from server
		buf.clear();
		channel.receive(buf);
		buf.flip();
		long timeGot = buf.getLong();
		System.out.println("client.timeGot: " + timeGot);
		channel.close();
	}


}

class UdpTimeServer implements Runnable {
	DatagramChannel channel;
	boolean stop = false;

	UdpTimeServer(InetSocketAddress serverAddress) throws IOException {
		channel = DatagramChannel.open();
		channel.socket().bind(serverAddress);
	}


	@Override
	public void run() {
		while (! stop) {
			try {
				// get client address by receiving data from any client
				ByteBuffer inBuf = ByteBuffer.allocate(1000);
				inBuf.clear();
				System.out.println("waiting for connection...");
				SocketAddress client = channel.receive(inBuf);

				// build data, and send back to client
				long currentMillis = System.currentTimeMillis();
				System.out.println("server: sending " + currentMillis + " to " + client);
				ByteBuffer outBuf = ByteBuffer.allocate(100);
				outBuf.clear();
				outBuf.putLong(currentMillis);
				outBuf.flip();
				channel.send(outBuf, client);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		System.out.println("Stopping udp time server...");
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
