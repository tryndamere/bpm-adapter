package org.bpm.db;

import org.bpm.db.po.BpmProcessConfig;

/**
 * Created by rocky on 14-5-14.
 */
public class BpmProcessConfigMapper extends BpmBaseDao<Object> {

    public void insert(BpmProcessConfig bpmProcessConfig){
        sqlSessionTemplate.insert("insertBpmProcessConfig" , bpmProcessConfig);
    }
}
