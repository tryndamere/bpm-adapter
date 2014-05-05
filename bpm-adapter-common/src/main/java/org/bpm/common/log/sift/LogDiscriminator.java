package org.bpm.common.log.sift;

import java.util.Map;

import org.bpm.common.utilities.StringUtilities;

import ch.qos.logback.classic.sift.MDCBasedDiscriminator;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 日志划分鉴别器
 * 
 * <pre>
 * 基于logback实现日志文件划分
 * </pre>
 */
public class LogDiscriminator extends MDCBasedDiscriminator {

    /**
     * 服务名常量
     */
    public static final String SERVICE_NAME = "SERVICE_NAME";

    @Override
    public String getDiscriminatingValue(ILoggingEvent event) {
        Map<String, String> mdcMap = event.getMDCPropertyMap();
        String logName = mdcMap.get(SERVICE_NAME);//获取服务名为文件名
        if (StringUtilities.hasText(logName)) {
            return StringUtilities.trimToEmpty(logName);
        }
        return getDefaultValue();
    }

}
