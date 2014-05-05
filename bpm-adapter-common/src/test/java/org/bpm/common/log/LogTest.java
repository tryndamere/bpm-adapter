package org.bpm.common.log;

import org.bpm.common.log.sift.LogDiscriminator;
import org.bpm.common.log.support.LogbackConfigurer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class LogTest {

    @BeforeClass
    public static void init() throws Exception {
        // 加载日志配置
        LogbackConfigurer.initLogging("classpath:org/bpm/common/log/logback-test.xml");
    }

    @Test
    public void testSIFTLogger() {
        Logger logger = LoggerFactory.getLogger("sift");
        logger.info("test start...");
        MDC.put(LogDiscriminator.SERVICE_NAME, "aa");// 设置变量
        logger.info("change mdc...");
        MDC.clear();
        logger.info("clean mdc...");
        logger.info("test end...");
    }

    @AfterClass
    public static void destory() {
        LogbackConfigurer.shutdownLogging();
    }

}
