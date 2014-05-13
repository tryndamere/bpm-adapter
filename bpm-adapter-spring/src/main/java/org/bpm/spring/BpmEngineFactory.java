package org.bpm.spring;

import org.bpm.common.exception.PlatformException;
import org.bpm.engine.BpmEngine;
import org.bpm.spring.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 引擎工厂,生成各种service接口,及
 * Created by serv on 14-5-7.
 */
public class BpmEngineFactory implements FactoryBean<BpmEngine>, ApplicationContextAware,InitializingBean, DisposableBean,ResourceLoaderAware {


    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected ApplicationContext applicationContext;

    private DataSource dataSource;

    private Properties properties;

    private PlatformTransactionManager transactionManager;

    /**
     * bpm服务提供者
     */
    protected BpmEngine bpmEngine;


    protected ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    /**
     * bpm 配置
     */
    protected Configuration configuration;


    public Configuration getConfiguration(){
        return this.configuration;
    }

    public Configuration newConfiguration(){
        if(configuration==null){
            Configuration bpmConfiguration =  new Configuration();
            bpmConfiguration.setApplicationContext(applicationContext);
            bpmConfiguration.setTransactionManager(transactionManager);
            bpmConfiguration.setDataSource(dataSource);
            return bpmConfiguration;
        }
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }




    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    public Properties getProperties() {
        if (this.properties == null) {
            this.properties = new Properties();
        }
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        BpmEngine be = buildBpmEngine();
        this.bpmEngine = wrapBpmEngineIfNecessary(be);
        afterBpmEngineCreation();
    }


    /**
     * 如果需要子类可以覆盖该方法,围绕be 进行一些自定义的事情
     * @param be bpmEngine
     * @return bpmEngine
     */
    protected BpmEngine wrapBpmEngineIfNecessary(BpmEngine be) {
        return be;
    }


    protected final BpmEngine getBpmEngine() {
        if (this.bpmEngine == null) {
            throw new IllegalStateException("BpmEngine 没有初始化");
        }
        return this.bpmEngine;
    }


    @Override
    public void destroy() throws PlatformException {
        log.info("销毁bpmEngine引擎!");
        try {
            beforeBpmEngineDestruction();
        }
        finally {
            this.bpmEngine.close();
        }
    }

    /**
     * 工厂对象
     */
    @Override
    public BpmEngine getObject() {
        return this.bpmEngine;
    }

    @Override
    public Class<BpmEngine> getObjectType() {
        return BpmEngine.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    /**
     * 覆盖该方法来触发引擎创建之前的事件
     * @throws Exception
     */
    protected void afterBpmEngineCreation() throws Exception {
    }

    /**
     * 覆盖该方法来触发销毁的事件
     */
    protected void beforeBpmEngineDestruction() {
    }



    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    }


    protected BpmEngine buildBpmEngine() throws Exception {

        configuration  = newConfiguration();
        if (this.properties != null) {
            configuration.addProperties(this.properties);
        }

        return buildBpmEngine(configuration);
    }

    private BpmEngine buildBpmEngine(Configuration cfg) {
        return cfg.buildBpmEngine();
    }


}
