package org.bpm.db.persistence;

/**
 * 是否有唯一索引
 */
public interface HasUniqueIndex<T> {

    /**
     * 获取索引
     * @return
     */
    public String getId();

    /**
     * 设置索引
     * @param id
     */
    public T setId(String id);

}
