package org.bpm.engine.impl.activiti.vo;


import org.bpm.engine.runtime.ActivityDefinition;

/**
 * Created by izerui.com on 14-5-26.
 */
public class BpmActivity implements ActivityDefinition {
    private String id;
    private String name;
    private String documentation;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
