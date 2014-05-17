package org.bpm.spring.cfg;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by izerui.com on 14-5-16.
 */
public class BpmNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("bpmEngine", new BpmBeanDefinitionParser());
    }
}
