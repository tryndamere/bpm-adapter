package org.bpm.common;

/**
 * 常量类
 * Created by serv on 14-5-13.
 */
public interface Environment {


    //流程引擎类型
    String ACTIVITI_ENGINE_TYPE = "activiti";
    String JBPM6_ENGINE_TYPE = "jbpm6";



    //自动扫描的包
    String BEAN_PACKAGE_SCAN = "org.bpm.engine";
    String REPOSITORY_PACKAGE_SCAN = "org.bpm.db";


    //bean的名称
    String BEAN_BPMRUNTIME = "org.bpm.engine.runtime.BpmRuntime";
    String BEAN_BPMDEFINITION = "org.bpm.engine.definition.BpmDefinition";


    String BEAN_BPMBASEDAO = "org.bpm.db.BpmBaseDao";
    String BEAN_BPMPROCESSCONFIGMAPPER = "org.bpm.db.config.BpmProcessConfigMapper";
}
