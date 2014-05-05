package org.bpm.db.persistence.operate;

/**
 * 查询参数
 */
public interface QueryParameter extends ParameterObject {

    /**
     * 设置每页数据
     * @param firstResult
     * @param maxResults
     * @return
     */
    public QueryParameter listPage(int firstResult, int maxResults);
    
    /**
     * 起始条数
     * @return
     */
    public int getFirstResult();

    /**
     * 第一条位置
     * @return
     */
    public int getFirstRow();

    /**
     * 最后一条位置
     * @return
     */
    public int getLastRow();

    /**
     * 最大条数
     * @return
     */
    public int getMaxResults();

    /**
     * 获取排序值
     * @return
     */
    public String getOrderBy();

}
