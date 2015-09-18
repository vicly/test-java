package vic.test.jdk.nio;

public class NIO {

	/*
	 * A scattering read from a channel: an operation reads data into more than one buffer.
	 * A gathering write to a channel: an operation writes data from more than one buffer into a single channl.
	 *
	 *
	 * Selector: examine one or more channels, and determine which channels are ready for reading/writing.
	 *           A single thread can manage multiple channels, and thus multiple network connections.
	 *
	 *
	 * Pipe: a one-way data connection between two threads
	 * 		Thread-A -> SinkChannel -> SourceChannel --> Thread-B
	 *
	 * 		SinkChannle, SourceChannel belongs to Pipe
	 *
	 */

}
