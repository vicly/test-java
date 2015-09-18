package vic.test.jdk.jmx.mxbean;


public interface QueueSamplerMXBean {
	QueueSample getQueueSample();
	void cleanQueue();
}
