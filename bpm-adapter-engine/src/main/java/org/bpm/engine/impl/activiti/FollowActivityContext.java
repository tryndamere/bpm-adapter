package org.bpm.engine.impl.activiti;

import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.bpm.engine.impl.activiti.vo.BpmActivity;
import org.bpm.engine.runtime.ActivityDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by serv on 14-5-25.
 */
public class FollowActivityContext {

    protected static ThreadLocal<Stack<ActivityImpl>> stackThreadLocal = new ThreadLocal<Stack<ActivityImpl>>();


    public static void addActivity(ActivityImpl activity) {
        Stack<ActivityImpl> stack = getActivityStack();
        stack.push(activity);
        stackThreadLocal.set(stack);
    }

    private static Stack<ActivityImpl> getActivityStack(){
        Stack<ActivityImpl> stack = stackThreadLocal.get();
        if(stack==null){
            stack = new Stack<ActivityImpl>();
        }
        return stack;
    }

    public static List<ActivityDefinition> getActivitys(){
        List<ActivityDefinition> activities = new ArrayList<ActivityDefinition>();
        Stack<ActivityImpl> stack = getActivityStack();
        while(!stack.isEmpty()){
            ActivityImpl activity = stack.pop();

            BpmActivity bpmActivity = new BpmActivity();
            bpmActivity.setId(activity.getId());
            bpmActivity.setName(String.valueOf(activity.getProperty("name")));
            bpmActivity.setDocumentation(String.valueOf(activity.getProperty("documentation")));
            bpmActivity.setType(String.valueOf(activity.getProperty("type")));

            activities.add(bpmActivity);
        }
        Collections.reverse(activities);
        return activities;
    }

}
