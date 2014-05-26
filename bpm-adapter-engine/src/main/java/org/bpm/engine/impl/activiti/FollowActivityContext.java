package org.bpm.engine.impl.activiti;

import org.activiti.engine.impl.pvm.process.ActivityImpl;

import java.util.*;

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

    public static List<ActivityImpl> getActivitys(){
        List<ActivityImpl> activities = new ArrayList<ActivityImpl>();
        Stack<ActivityImpl> stack = getActivityStack();
        while(!stack.isEmpty()){
            activities.add(stack.pop());
        }
        Collections.reverse(activities);
        return activities;
    }
}
