package org.bpm.db.persistence.operate;

import java.io.Serializable;

/**
 * 操作参数
 */
public interface ParameterObject extends Serializable {

    /**
     * 获取参数
     * @return
     */
    public Object getParameter();

    /**
     * 获取表尾缀
     * @return
     */
    public String getTableSuffix();

}
