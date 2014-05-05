package org.bpm.db.persistence;

import java.io.Serializable;

/**
 * 持久对象接口
 */
public interface PersistentObject extends Serializable {

    /**
     * 持久状态
     * @return
     */
    public Object getPersistentState();

    /**
     * 参数校验
     */
    public void validates();

}
