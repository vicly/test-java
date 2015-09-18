package vic.test.jdk.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTrail {

	public static void main(String[] args) throws IOException {

		final Pipe pipe = Pipe.open();

		class ControlFlag {
			boolean writeJobStop = false;
			boolean readJobStop = false;
			void stop() {
				writeJobStop = true;
				readJobStop = true;
			}
		}

		final ControlFlag flag = new ControlFlag();


		Runnable writeJob =  new Runnable() {
			@Override
			public void run() {
				while (! flag.writeJobStop) {
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					Pipe.SinkChannel sink = pipe.sink();
					String data = "nanoTime is " + System.nanoTime() + System.lineSeparator();
					ByteBuffer buf = ByteBuffer.allocate(100);
					buf.clear();
					buf.put(data.getBytes());
					buf.flip();
					while (buf.hasRemaining()) {
						try {
							sink.write(buf);
						} catch (IOException ioe) {
							throw new IllegalStateException("Failed write data to pipe", ioe);
						}
					}

					// ***
					// For Non-blocking I/O, at here, the data writing might still be in progress
					// the current thread can do something else, while the writing is in progress
				}

				// ensure readJob.sys.print complete
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(">> writeJob stopped");
			}
		};

		Runnable readJob = new Runnable() {
			@Override
			public void run() {
				while (! flag.readJobStop) {
					Pipe.SourceChannel source = pipe.source();
					ByteBuffer buf = ByteBuffer.allocate(100);
					try {
						source.read(buf);
						buf.flip();
						while (buf.hasRemaining()) {
							System.out.print((char) buf.get());
						}
					} catch (IOException ioe) {
						throw new IllegalStateException("Failed read data from pipe", ioe);
					}
				}

				System.out.println(">> readJob stopped");
			}
		};


		Thread writeThread = new Thread(writeJob);
		writeThread.start();

		Thread readThread = new Thread(readJob);
		readThread.start();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		flag.stop();
	}
}
