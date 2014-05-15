package org.bpm.spring;

import org.bpm.common.exception.PlatformException;
import org.bpm.engine.BpmEngine;
import org.bpm.spring.cfg.Configuration;
import org.bpm.engine.Environment;
import org.bpm.spring.initialize.init.BeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 引擎工厂,生成各种service接口,及
 * Created by serv on 14-5-7.
 */
public class BpmEngineFactory implements FactoryBean<BpmEngine>,BeanFactoryAware, ApplicationContextAware,InitializingBean, DisposableBean {


    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected ApplicationContext applicationContext;

    private DataSource dataSource;

    private Properties properties;

    private Resource[] locations;

    private String[] beanPackagesToScan;

    private PlatformTransactionManager transactionManager;

    private DefaultListableBeanFactory beanFactory;

    /**
     * bpm服务提供者
     */
    protected BpmEngine bpmEngine;


    protected BeanManager beanManager = new BeanManager();

    /**
     * bpm 配置
     */
    protected Configuration configuration;


    public Configuration getConfiguration(){
        return this.configuration;
    }

    public Configuration newConfiguration(){
        if(configuration==null){
            return new Configuration();
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



    protected BpmEngine buildBpmEngine() throws Exception {

        configuration  = newConfiguration();

        if (this.properties != null) {
            configuration.addProperties(this.properties);
        }

        if(this.locations!=null){
            for (Resource resource:locations){

                Properties props = new Properties();

                PropertiesLoaderUtils.fillProperties(
                        props, resource);

                configuration.addProperties(props);

            }
        }


        buildConfiguration();

        return buildBpmEngine(configuration);
    }

    private void buildConfiguration() {
        configuration.setApplicationContext(applicationContext);
        configuration.setTransactionManager(transactionManager);
        configuration.setDataSource(dataSource);

    }

    private BpmEngine buildBpmEngine(Configuration cfg) {
        return cfg.buildBpmEngine();
    }


    public Resource[] getLocations() {
        return locations;
    }

    public void setLocations(Resource[] locations) {
        this.locations = locations;
    }


    /**
     * 将一些必要的Bean声明到spring 容器中
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        this.beanFactory = (DefaultListableBeanFactory) beanFactory;

        beanManager.setBeanFactory(this.beanFactory);
        beanManager.setBeanPackagesToScan(this.mergeBeanPackagesToScan());

        beanManager.setProcessEngineType(getProperties().getProperty(Environment.ENGINE_TYPE));

        beanManager.buildBpmBeans().init();
    }

    public String[] mergeBeanPackagesToScan() {
        if(beanPackagesToScan==null){
            return new String[]{Environment.BEAN_PACKAGE_SCAN,Environment.REPOSITORY_PACKAGE_SCAN};
        }else{
            List<String> strings = Arrays.asList(beanPackagesToScan);
            strings.add(Environment.BEAN_PACKAGE_SCAN);
            strings.add(Environment.REPOSITORY_PACKAGE_SCAN);
            return (String[]) strings.toArray();
//            return ArrayUtils.add(beanPackagesToScan,Environment.BEAN_PACKAGE_SCAN);
        }
    }

    public void setBeanPackagesToScan(String[] beanPackagesToScan) {
        this.beanPackagesToScan = beanPackagesToScan;
    }


}
