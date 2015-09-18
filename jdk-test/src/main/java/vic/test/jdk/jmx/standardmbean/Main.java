package vic.test.jdk.jmx.standardmbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Main {

	/**
	 * run this application
	 * open JConsole
	 * change "ThreadCount" to "0" to stop this application
	 */

	public static void main(String[] args) throws Exception {
		// get MBean server
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		// register MBean
		SystemConfig mBean = new SystemConfig(20);
		ObjectName name = new ObjectName("vic.test.jdk.jmx:type=SystemConfig");
		mbs.registerMBean(mBean, name);

		while (mBean.getThreadCount() != 0) {
			Thread.sleep(3000);
			System.out.println("Current thread count is " + mBean.getThreadCount());
		}
	}

}
