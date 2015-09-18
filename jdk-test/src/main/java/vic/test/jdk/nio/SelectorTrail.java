package vic.test.jdk.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SelectorTrail {

	public static void main(String[] args) throws IOException {

		System.out.println((1 << 0));
		System.out.println((1 << 2));
		System.out.println((1 << 3));
		System.out.println((1 << 4));

		Selector selector = Selector.open();

		SocketChannel channel = null;
		/*
		 * must in Non-Blocking mode, FileChannel can't be used by selector, since it's always in blocking mode
		 *
		 * Different events:
		 *
		 * SelectionKey.OP_CONNECT: a channel has connected successfully to another server
		 * SelectionKey.OP_ACCEPT: a server channel accepts an incoming connection
		 * SelectionKey.OP_READ: a channel has data ready to be read
		 * SelectionKey.OP_WRITE: a channel is ready to write data
		 *
		 */
		channel.configureBlocking(false);
		SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
		channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE); // more than one

		selector.select();

	}

}
