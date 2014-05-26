package org.bpm.engine.impl.activiti.listener;

import org.activiti.engine.delegate.event.ActivitiActivityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.bpm.engine.impl.activiti.FollowActivityContext;

/**
 * Created by serv on 14-5-25.
 */
public class FollowActivityEventListener implements ActivitiEventListener {

    @Override
    public void onEvent(ActivitiEvent ev) {

        ActivitiActivityEvent event = (ActivitiActivityEvent) ev;

        ProcessDefinitionImpl processDefinition = (ProcessDefinitionImpl) event.getEngineServices().getRepositoryService().getProcessDefinition(event.getProcessDefinitionId());
        ActivityImpl activity = processDefinition.findActivity(event.getActivityId());
        FollowActivityContext.addActivity(activity);

    }

    @Override
    public boolean isFailOnException() {
        return true;
    }
}
