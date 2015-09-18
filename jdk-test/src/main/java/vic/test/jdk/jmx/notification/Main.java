package vic.test.jdk.jmx.notification;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Main {
	public static void main(String[] args) throws Exception {
		// get MBean server
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		// register MBean
		Hello mxbean = new Hello();
		ObjectName name = new ObjectName("vic.test.jdk.jmx.notification:type=Hello");
		mbs.registerMBean(mxbean, name);

		System.out.println("Waiting...");
		Thread.sleep(Long.MAX_VALUE);
	}
}
