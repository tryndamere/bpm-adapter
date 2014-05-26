package org.bpm.engine;

/**
 * Created by serv on 14-5-13.
 */
public interface Environment {

    String ENGINE_TYPE = "bpm_engine_type";

    String ACTIVITI_ENGINE_TYPE = "activiti";
    String JBPM4_ENGINE_TYPE = "jbpm4";
    String JBPM6_ENGINE_TYPE = "jbpm6";



    String BEAN_PACKAGE_SCAN = "org.bpm.engine";
    String REPOSITORY_PACKAGE_SCAN = "org.bpm.db";


}
