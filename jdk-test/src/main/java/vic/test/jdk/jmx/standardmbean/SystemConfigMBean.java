package vic.test.jdk.jmx.standardmbean;

public interface SystemConfigMBean {
	/*
	 * any method starting with get/set are considered as
	 * attributes.
	 */
	void setThreadCount(int threadCount);
	int getThreadCount();

	/*
	 * non-get/set as operation
	 */
	String doConfig();
}
