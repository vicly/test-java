package vic.test.jdk.jmx.mxbean;

import java.lang.management.ManagementFactory;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Main {

	/**
	 * Run me, and then open JConsole
	 */
	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("vic.test.jdk.jmx.mxbean:type=QueueSampler");
		Queue<String> queue = new ArrayBlockingQueue<>(10);
		queue.add("req-1");
		queue.add("req-2");
		queue.add("req-3");
		QueueSampler mxbean = new QueueSampler(queue);
		mbs.registerMBean(mxbean, name);

		System.out.println("Waiting...");
		Thread.sleep(Long.MAX_VALUE);
	}

}
