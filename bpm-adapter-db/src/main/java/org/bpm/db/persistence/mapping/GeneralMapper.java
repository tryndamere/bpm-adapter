package org.bpm.db.persistence.mapping;

import java.util.List;

import org.bpm.db.persistence.PersistentObject;
import org.bpm.db.persistence.operate.ParameterObject;
import org.bpm.db.persistence.operate.QueryParameter;


/**
 * 通用操作映射
 * 
 * @param <T> 实体类
 */
public interface GeneralMapper<T extends PersistentObject> {

    /**
     * 插入实体数据
     * @param parameter 实体数据
     * @return
     */
    public int insert(ParameterObject parameter);

    /**
     * 删除实体数据
     * @param parameter 实体数据
     * @return
     */
    public int delete(ParameterObject parameter);

    /**
     * 修改实体数据
     * @param t 实体数据
     * @return
     */
    public int alter(ParameterObject parameter);

    /**
     * 根据查询参数获取唯一实体数据
     * @param queryParameter 查询参数
     * @return
     */
    public T singleResult(QueryParameter queryParameter);

    /**
     * 根据查询参数获取所有实体数据
     * @param queryParameter 查询参数
     * @return
     */
    public List<T> list(QueryParameter queryParameter);

    /**
     * 根据查询参数获取实体数据总量
     * @param queryParameter 查询参数
     * @return
     */
    public long count(QueryParameter queryParameter);

    /**
     * 根据查询参数判断数据表是否存在
     * @param queryParameter 查询参数
     */
    public void isTableExist(QueryParameter queryParameter);

}
