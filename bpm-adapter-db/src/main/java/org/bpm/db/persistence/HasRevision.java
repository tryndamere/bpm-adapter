package org.bpm.db.persistence;

/**
 * 是否有版本号参数
 */
public interface HasRevision {

    /**
     * 获取版本号
     * @return
     */
    public int getRevision();

    /**
     * 设置版本号
     * @param revision
     */
    public void setRevision(int revision);

    /**
     * 获取下一个版本号
     * @return
     */
    public int getRevisionNext();

}
