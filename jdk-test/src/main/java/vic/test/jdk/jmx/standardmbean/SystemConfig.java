package vic.test.jdk.jmx.standardmbean;

public class SystemConfig implements SystemConfigMBean {

	private int threadCount;

	public SystemConfig(int threadCount) {
		setThreadCount(threadCount);
	}

	@Override
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	@Override
	public int getThreadCount() {
		return this.threadCount;
	}

	@Override
	public String doConfig() {
		return "No of threads = " + this.threadCount;
	}

}
