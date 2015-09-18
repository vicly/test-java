package vic.test.jdk.jmx.notification;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Hello extends NotificationBroadcasterSupport implements HelloMBean {

	private final String name = "Reginald";
	private long sequenceNumber = 1;
	private int cacheSize = 200;

	@Override
	public void sayHello() {
		System.out.println("Hello, World");
	}

	@Override
	public int add(int x, int y) {
		return x + y;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getCacheSize() {
		return cacheSize;
	}

	@Override
	public synchronized void setCacheSize(int size) {
		int oldSize = size;
		this.cacheSize = size;
		System.out.println("Cache size now " + this.cacheSize);

		Notification n = new AttributeChangeNotification(this,
				this.sequenceNumber++ ,
				System.currentTimeMillis(),
				"CacheSize changed",
				"CacheSize",
				"int",
				oldSize,
				this.cacheSize);

		sendNotification(n);
	}

	@Override
	public MBeanNotificationInfo[] getNotificationInfo() {
		String[] types = new String[] {
				AttributeChangeNotification.ATTRIBUTE_CHANGE
		};
		String name = AttributeChangeNotification.class.getName();
		String description = "An attribute of this MBean has changed";
		MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
		return new MBeanNotificationInfo[] { info };
	}

}



