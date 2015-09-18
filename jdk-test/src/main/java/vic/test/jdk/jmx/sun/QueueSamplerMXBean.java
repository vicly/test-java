package vic.test.jdk.jmx.sun;


public interface QueueSamplerMXBean {
    public QueueSample getQueueSample();
    public void clearQueue();
}