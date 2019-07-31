package vic.test.springboot.app.jersey;

import com.google.common.base.MoreObjects;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.util.unit.DataSize;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Vic Liu
 */
public class ClientConfiguration {
    private String baseUrl;

    private boolean httpLogEnabled;

    @DurationUnit(ChronoUnit.SECONDS)
    private Duration sessionTimeout;

    @NotNull
    private Duration readTimeout;

    private DataSize payloadSizeThreshold;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public boolean isHttpLogEnabled() {
        return httpLogEnabled;
    }

    public void setHttpLogEnabled(boolean httpLogEnabled) {
        this.httpLogEnabled = httpLogEnabled;
    }

    public Duration getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Duration sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }

    public DataSize getPayloadSizeThreshold() {
        return payloadSizeThreshold;
    }

    public void setPayloadSizeThreshold(DataSize payloadSizeThreshold) {
        this.payloadSizeThreshold = payloadSizeThreshold;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("baseUrl", baseUrl)
                .add("httpLogEnabled", httpLogEnabled)
                .add("sessionTimeout", sessionTimeout)
                .add("readTimeout", readTimeout)
                .add("payloadSizeThreshold", payloadSizeThreshold)
                .toString();
    }
}
