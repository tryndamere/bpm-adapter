package org.bpm.db.config;

import org.bpm.db.BpmBaseDao;
import org.bpm.db.config.po.BpmProcessConfig;

/**
 * Created by rocky on 14-5-14.
 */
public class BpmProcessConfigMapper extends BpmBaseDao<BpmProcessConfig> {

    public void insert(BpmProcessConfig bpmProcessConfig){
        sqlSessionTemplate.insert("insertBpmProcessConfig" , bpmProcessConfig);
    }
}
