package org.bpm.db.persistence.operate;

import java.io.Serializable;

/**
 * 查询属性
 */
public interface QueryProperty extends Serializable {

    /**
     * 属性名
     * @return
     */
    String getName();

}
