package org.bpm.db.config;

import org.bpm.common.Environment;
import org.bpm.common.stereotype.DynamicBean;
import org.bpm.db.BpmBaseDao;
import org.bpm.db.config.po.BpmProcessConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rocky on 14-5-14.
 */
@DynamicBean(beanName = Environment.BEAN_BPMPROCESSCONFIGMAPPER,parent = Environment.BEAN_BPMBASEDAO)
public class BpmProcessConfigMapper extends BpmBaseDao<BpmProcessConfig> {

    public void insert(BpmProcessConfig bpmProcessConfig){
        sqlSessionTemplate.insert("insertBpmProcessConfig" , bpmProcessConfig);
    }

    /**
     * 任意获取一个结束节点
     * @return
     */
    public BpmProcessConfig endTaskDefkey(String processDefKey){
        return searchBpmProcessConfigByTaskDefKey(BpmProcessConfig.TASK_DEF_TYPE.END , processDefKey);
    }

    /**
     * 任意获取一个结束节点
     * @return
     */
    public BpmProcessConfig startTaskDefkey(String processDefKey){
        return searchBpmProcessConfigByTaskDefKey(BpmProcessConfig.TASK_DEF_TYPE.START , processDefKey);
    }

    private BpmProcessConfig searchBpmProcessConfigByTaskDefKey(BpmProcessConfig.TASK_DEF_TYPE taskDefType , String processDefKey){
        Map<String,String> parameterMap = new HashMap<String, String>();
        parameterMap.put("taskDefType", taskDefType.getValue());
        parameterMap.put("processDefKey" , processDefKey);
        List<BpmProcessConfig> bpmProcessConfigs = sqlSessionTemplate.selectList("searchBpmProcessConfigByTaskDefKey" , parameterMap);
        if (bpmProcessConfigs != null && bpmProcessConfigs.size() > 0){
            return bpmProcessConfigs.get(0);
        }
        throw new NullPointerException("未配置"+taskDefType.getName()+"节点！");
    }
}
