package org.bpm.common.log.support;

import java.io.FileNotFoundException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import ch.qos.logback.classic.BasicConfigurator;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
 * Logback配置加载器
 */
public abstract class LogbackConfigurer {

    private static final String MODULE = LogbackConfigurer.class.getName();

    /**
     * 加载logback配置
     * @param location 文件路径
     * @throws FileNotFoundException 文件不存在异常
     */
    public static void initLogging(String location) throws FileNotFoundException {
        if (LoggerFactory.getILoggerFactory() instanceof LoggerContext) {
            String resolvedLocation = SystemPropertyUtils.resolvePlaceholders(location);
            URL url = ResourceUtils.getURL(resolvedLocation);
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            try {
                JoranConfigurator configurator = new JoranConfigurator();
                configurator.setContext(loggerContext);
                loggerContext.reset();
                configurator.doConfigure(url);
            } catch (Throwable e) {
                BasicConfigurator.configureDefaultContext();
                Logger log = LoggerFactory.getLogger(MODULE);
                log.warn("Logback configure fail!", e);
            }
        }
    }

    /**
     * 停止日志
     */
    public static void shutdownLogging() {
        if (LoggerFactory.getILoggerFactory() instanceof LoggerContext) {
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            loggerContext.stop();
        }
    }

}
