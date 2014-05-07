package org.bpm.spring;

import org.bpm.engine.runtime.BpmRuntime;
import org.bpm.spring.cfg.Configuration;
import org.bpm.spring.impl.ActivitiRuntimeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by serv on 14-5-7.
 */
public enum EngineType implements ServiceCreator{

    ACTIVITI(){
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration) {

            BpmRuntime bpmRuntime = new ActivitiRuntimeImpl();
            autowired(bpmRuntime, configuration);
            return bpmRuntime;
        }
    },JBPM4{
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration) {
            return null;
        }
    },JBPM6{
        @Override
        public BpmRuntime createBpmRuntime(Configuration configuration) {
            return null;
        }
    };

    private static Logger logger = LoggerFactory.getLogger(EngineType.class);

    private final static Set<Class<? extends Annotation>> autowiredAnnotationTypes =
            new LinkedHashSet<Class<? extends Annotation>>();
    static{
        autowiredAnnotationTypes.add(Autowired.class);
        autowiredAnnotationTypes.add(Value.class);
        ClassLoader cl = EngineType.class.getClassLoader();
        try {
            autowiredAnnotationTypes.add((Class<? extends Annotation>) cl.loadClass("javax.inject.Inject"));
            logger.info("JSR-330 'javax.inject.Inject' annotation found and supported for autowiring");
        }
        catch (ClassNotFoundException ex) {
            // JSR-330 API not available - simply skip.
        }
    }

    private Annotation findAutowiredAnnotation(AccessibleObject ao) {
        for (Class<? extends Annotation> type : autowiredAnnotationTypes) {
            Annotation annotation = AnnotationUtils.getAnnotation(ao, type);
            if (annotation != null) {
                return annotation;
            }
        }
        return null;
    }


    public void autowired(Object object,Configuration configuration) {

        //ioc
        if(object instanceof ApplicationContextAware){
            ((ApplicationContextAware) object).setApplicationContext(configuration.getApplicationContext());
        }

        //ioc
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for(Field field:declaredFields){
           Annotation ann = findAutowiredAnnotation(field);
            if(ann!=null){
                try {
                    ReflectionUtils.makeAccessible(field);
                    field.set(object, configuration.getApplicationContext().getBean(field.getType()));
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }



}
interface ServiceCreator{

    public BpmRuntime createBpmRuntime(Configuration configuration);
}
