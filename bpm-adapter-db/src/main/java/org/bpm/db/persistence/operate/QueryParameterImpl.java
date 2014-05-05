package org.bpm.db.persistence.operate;

import org.bpm.common.exception.impl.NonTransientDataAccessException;
import org.bpm.common.utilities.StringUtilities;
import org.bpm.db.persistence.PersistentObject;

@SuppressWarnings("serial")
public abstract class QueryParameterImpl<T extends QueryParameter, P extends PersistentObject> implements
        QueryParameter {
    /**
     * 参数
     */
    protected P parameter;
    /**
     * 排序信息
     */
    protected String orderBy;
    /**
     * 起始条数
     */
    protected int firstResult = 0;
    /**
     * 最大条数
     */
    protected int maxResults = Integer.MAX_VALUE;
    /**
     * 表名尾缀
     */
    protected String tableSuffix = "";
    /**
     * 是否分页
     */
    protected boolean isPage = false;

    public QueryParameterImpl() {
    }

    public QueryParameterImpl(P parameter) {
        this.parameter = parameter;
    }

    public QueryParameterImpl(P parameter, int firstResult, int maxResults) {
        this(parameter);
        this.firstResult = firstResult;
        this.maxResults = maxResults;
    }

    @Override
    public Object getParameter() {
        return parameter;
    }

    @Override
    public int getFirstResult() {
        return firstResult;
    }

    @Override
    public int getFirstRow() {
        return firstResult + 1;
    }

    @Override
    public int getLastRow() {
        if (maxResults == Integer.MAX_VALUE) {
            return maxResults;
        }
        return firstResult + maxResults + 1;
    }

    @Override
    public int getMaxResults() {
        return maxResults;
    }

    @Override
    public String getOrderBy() {
        return StringUtilities.hasText(orderBy) ? orderBy : defaultOrderBy();
    }

    @Override
    public String getTableSuffix() {
        return StringUtilities.hasText(tableSuffix) ? tableSuffix : "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public T listPage(int firstResult, int maxResults) {
        isPage = true;
        setFirstResult(firstResult);
        setMaxResults(maxResults);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setFirstResult(int firstResult) {
        this.firstResult = firstResult;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setParameter(P parameter) {
        this.parameter = parameter;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setTableSuffix(String tableSuffix) {
        this.tableSuffix = tableSuffix;
        return (T) this;
    }

    /**
     * 按属性顺序排序
     * @param orderProperty
     * @param direction
     * @return
     */
    @SuppressWarnings("unchecked")
    protected T orderBy(QueryProperty orderProperty, Direction direction) {
        if (orderProperty == null) {
            throw new NonTransientDataAccessException(
                    "You should call any of the 'orderBy' methods first before specifying a direction");
        }
        if (direction == null) {
            direction = Direction.ASCENDING;
        }
        addOrder(orderProperty.getName(), direction.getName());
        return (T) this;
    }

    /**
     * 添加OrderBy信息
     * @param column 列名
     * @param sortOrder 排序方式
     */
    protected void addOrder(String column, String sortOrder) {
        if (orderBy == null) {
            orderBy = "";
        } else {
            orderBy = orderBy + ", ";
        }
        orderBy = orderBy + column + " " + sortOrder;
    }

    /**
     * 默认排序
     * @return
     */
    protected abstract String defaultOrderBy();

}
