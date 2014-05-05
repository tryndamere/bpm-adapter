package org.bpm.db.persistence;

import java.util.Date;

/**
 * 是否有更新时间参数
 */
public interface HasUpdatetime {

    /**
     * 获取更新时间
     * @return
     */
    public Date getUpdateTime();

    /**
     * 设置更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime);

    /**
     * 获取当前时间
     * @return
     */
    public Date getCurrentTime();

}
