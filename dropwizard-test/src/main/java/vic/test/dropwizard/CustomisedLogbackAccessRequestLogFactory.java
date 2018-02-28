package vic.test.dropwizard;

import ch.qos.logback.access.spi.IAccessEvent;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.common.collect.ImmutableList;
import io.dropwizard.logging.AppenderFactory;
import io.dropwizard.logging.ConsoleAppenderFactory;
import io.dropwizard.logging.async.AsyncAppenderFactory;
import io.dropwizard.logging.filter.LevelFilterFactory;
import io.dropwizard.logging.filter.NullLevelFilterFactory;
import io.dropwizard.logging.layout.LayoutFactory;
import io.dropwizard.request.logging.LogbackAccessRequestLog;
import io.dropwizard.request.logging.RequestLogFactory;
import io.dropwizard.request.logging.async.AsyncAccessEventAppenderFactory;
import io.dropwizard.request.logging.layout.LogbackAccessRequestLayout;
import org.eclipse.jetty.server.RequestLog;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.TimeZone;

/**
 * @author Vic Liu
 */
@JsonTypeName("custom")
public class CustomisedLogbackAccessRequestLogFactory implements RequestLogFactory {

    @Valid
    @NotNull
    private ImmutableList<AppenderFactory<IAccessEvent>> appenders = ImmutableList.of(new ConsoleAppenderFactory<>());

    @JsonProperty
    public ImmutableList<AppenderFactory<IAccessEvent>> getAppenders() {
        return appenders;
    }

    @JsonProperty
    public void setAppenders(ImmutableList<AppenderFactory<IAccessEvent>> appenders) {
        this.appenders = appenders;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return !appenders.isEmpty();
    }

    @Override
    public RequestLog build(String name) {
        final Logger logger = (Logger) LoggerFactory.getLogger("http.request");
        logger.setAdditive(false);

        final LoggerContext context = logger.getLoggerContext();

        final LogbackAccessRequestLog requestLog = new LogbackAccessRequestLog();

        final LevelFilterFactory<IAccessEvent> levelFilterFactory = new NullLevelFilterFactory<>();
        final AsyncAppenderFactory<IAccessEvent> asyncAppenderFactory = new AsyncAccessEventAppenderFactory();
        final LayoutFactory<IAccessEvent> layoutFactory = new MyLogbackAccessRequestLayoutFactory();

        for (AppenderFactory<IAccessEvent> output : appenders) {
            requestLog.addAppender(output.build(context, name, layoutFactory, levelFilterFactory, asyncAppenderFactory));
        }

        return requestLog;
    }

    private static class MyLogbackAccessRequestLayoutFactory implements LayoutFactory<IAccessEvent> {
        @Override
        public PatternLayoutBase<IAccessEvent> build(LoggerContext context, TimeZone timeZone) {
            return new MyLogbackAccessRequestLayout(context, timeZone);
        }
    }

    private static class MyLogbackAccessRequestLayout extends LogbackAccessRequestLayout {
        public MyLogbackAccessRequestLayout(Context context, TimeZone timeZone) {
            super(context, timeZone);
        }

        @Override
        public String doLayout(IAccessEvent event) {
            // event.getRequestURL()

            //return "GOOD >> " + super.doLayout(event);
            return PCI.mask(super.doLayout(event));
        }
    }


}

