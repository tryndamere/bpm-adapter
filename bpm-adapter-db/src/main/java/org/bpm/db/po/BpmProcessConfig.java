package org.bpm.db.po;

/**
 * Created by rocky on 14-5-14.
 */
public class BpmProcessConfig {

    public enum TASK_DEF_TYPE {

        START("0") , END("1") , NORMAL("2") ;

        private String value ;

        TASK_DEF_TYPE(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
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
