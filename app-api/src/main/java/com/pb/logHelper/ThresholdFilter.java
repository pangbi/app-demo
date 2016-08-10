package com.pb.logHelper;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangqiang on 2016/8/4.
 */
public class ThresholdFilter extends Filter<ILoggingEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ThresholdFilter.class);

    Level level;
    String appName;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }

        String lvl = AppLogConfig.MAP.get(appName);
        Level configLevel = Level.toLevel(lvl, this.level);
        logger.info("app:{} zkLevel:{} defLevel:{}", appName, lvl, configLevel);
        //logger.error("app:{} zkLevel:{} defLevel:{}", appName, lvl, configLevel);
        this.level = configLevel;
        if (event.getLevel().isGreaterOrEqual(configLevel)) {
            return FilterReply.ACCEPT;
        } else {
            return FilterReply.DENY;
        }
    }

    public void setLevel(String level) {
        this.level = Level.toLevel(level);
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void start() {
        if (this.level != null) {
            super.start();
        }
    }

}
