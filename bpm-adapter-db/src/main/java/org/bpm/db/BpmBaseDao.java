package org.bpm.db;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.bpm.common.stereotype.DynamicBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by rocky on 14-5-14.
 */
@DynamicBean(beanName = "org.bpm.db.BpmBaseDao",isAbstract = true)
public abstract class BpmBaseDao<T> {

    protected static final Integer BATCH_SIZE = new Integer(1000);

    private static final Logger LOGGER = LoggerFactory.getLogger(BpmBaseDao.class);

    public SqlSessionTemplate sqlSessionTemplate ;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    protected int batchInsert(List<T> insertObjects , String statement , Integer batchSize){
        SqlSession sqlSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        if (batchSize == null || batchSize == 0){
            batchSize = BATCH_SIZE ;
        }
        int execCount = 0;
        for (int i = 0 , size = insertObjects.size(); i < size ; i++){
            sqlSession.insert(statement , insertObjects);
            if ((i != 0 && i % batchSize == 0) || i == size -1){
                List<BatchResult> batchResults = sqlSession.flushStatements();
                execCount = batchResults.size() ;
                sqlSession.commit();
                sqlSession.clearCache();
                LOGGER.debug("batch insert , current commit size : {} " , execCount );
            }
        }
        sqlSession.close();
        return execCount ;
    }

    protected int batchUpdate(List<T> updateObjects , String statement , Integer batchSize){
        SqlSession sqlSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH , TransactionIsolationLevel.NONE);
        if (batchSize == null || batchSize == 0){
            batchSize = BATCH_SIZE ;
        }
        int execCount = 0;
        for (int i = 0 , size = updateObjects.size(); i < size ; i++){
            sqlSession.update(statement, updateObjects);
            if ((i != 0 && i % batchSize == 0) || i == size -1){
                List<BatchResult> batchResults = sqlSession.flushStatements();
                execCount = batchResults.size() ;
                sqlSession.commit();
                sqlSession.clearCache();
                LOGGER.debug("batch insert , current commit size : {} " , execCount );
            }
        }
        sqlSession.close();
        return execCount;
    }

    protected int batchDelete(List<T> delObjects , String statement , Integer batchSize){
        SqlSession sqlSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH , TransactionIsolationLevel.NONE);
        if (batchSize == null || batchSize == 0){
            batchSize = BATCH_SIZE ;
        }
        int execCount = 0;
        for (int i = 0 , size = delObjects.size(); i < size ; i++){
            sqlSession.delete(statement, delObjects);
            if ((i != 0 && i % batchSize == 0) || i == size -1){
                List<BatchResult> batchResults = sqlSession.flushStatements();
                execCount = batchResults.size() ;
                sqlSession.commit();
                sqlSession.clearCache();
                LOGGER.debug("batch insert , current commit size : {} " , execCount );
            }
        }
        sqlSession.close();
        return execCount;
    }
}
