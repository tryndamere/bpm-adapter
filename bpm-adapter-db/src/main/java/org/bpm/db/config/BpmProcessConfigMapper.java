package org.bpm.db.config;

import org.bpm.common.BeanName;
import org.bpm.common.stereotype.DynamicBean;
import org.bpm.db.BpmBaseDao;
import org.bpm.db.config.po.BpmProcessConfig;

/**
 * Created by rocky on 14-5-14.
 */
@DynamicBean(beanName = BeanName.BEAN_NAME_PROCESS_CONFIG_MAPPER,parent = BeanName.BEAN_NAME_BPM_BASEDAO)
public class BpmProcessConfigMapper extends BpmBaseDao<BpmProcessConfig> {

    public void insert(BpmProcessConfig bpmProcessConfig){
        sqlSessionTemplate.insert("insertBpmProcessConfig" , bpmProcessConfig);
    }
}
