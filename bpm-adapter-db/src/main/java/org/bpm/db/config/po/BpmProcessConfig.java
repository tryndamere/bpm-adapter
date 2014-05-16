package org.bpm.db.config.po;

/**
 * Created by rocky on 14-5-14.
 */
public class BpmProcessConfig {

    public enum TASK_DEF_TYPE {

        START("0" , "开始") , END("1" , "结束") , NORMAL("2" , "一般") ;

        private String value ;
        private String name ;

        TASK_DEF_TYPE(String value ,String name){
            this.name = name ;
            this.value = value;
        }

        public String getValue(){
            return value;
        }

        public String getName(){
            return name;
        }
    }

    private String id ;

    private String processDefKey ;

    private String taskDefKey ;

    private String taskDefType;

    private String taskConfig;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessDefKey() {
        return processDefKey;
    }

    public void setProcessDefKey(String processDefKey) {
        this.processDefKey = processDefKey;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public String getTaskDefType() {
        return taskDefType;
    }

    public void setTaskDefType(String taskDefType) {
        this.taskDefType = taskDefType;
    }

    public String getTaskConfig() {
        return taskConfig;
    }

    public void setTaskConfig(String taskConfig) {
        this.taskConfig = taskConfig;
    }
}
