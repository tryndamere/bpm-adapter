package org.bpm.common.stereotype;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.annotation.*;

/**
 * 声明该类为一个bean,bpm会根据设置的各个属性,加入spring bean管理
 * Created by izerui.com on 14-5-15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicBean {

    /**
     * 是否开启自动注入
     * @return
     */
    int autowireMode() default AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

    /**
     * bean的名称
     * @return
     */
    String beanName();

    /**
     * 工厂bean的名称
     * @return
     */
    String factoryBeanName() default "";

    /**
     * 获取bean的工厂方法名称
     * @return
     */
    String factoryMethodName() default "";

    /**
     * bean的父类bean名称
     * @return
     */
    String parent() default "";

    /**
     * 依赖bean,声明的依赖bean会在该bean创建之前先初始化
     * @return
     */
    String dependsOn() default "";


    /**
     * 是否是抽象
     * @return
     */
    boolean isAbstract() default false;

    /**
     * 获取对象之前是否检查依赖关系
     * @return
     */
    int dependencyCheck() default RootBeanDefinition.DEPENDENCY_CHECK_NONE;

    /**
     * 初始化方法名
     * @return
     */
    String initMethodName() default "";

    /**
     * 是否延迟初始化
     * @return
     */
    boolean lazyInit() default false;

    /**
     * 默认单例
     * @return
     */
    String scope() default ConfigurableBeanFactory.SCOPE_SINGLETON;


    /**
     * 默认所属的流程引擎,如果为空,会加载, 如果不为空,并且跟当前properties的 bpm_engine_type 不一致,则不加载
     * @return
     */
    String processEngineType() default "";


}
