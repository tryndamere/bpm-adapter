package org.bpm.db.support;

import java.util.EnumMap;
import java.util.Map;

import org.bpm.common.utilities.StringUtilities;

/**
 * 数据库SQL语句工厂
 */
public class DbSqlFactory {

    /**
     * 特殊的数据库前置限制声明
     */
    public static final Map<DatabaseType, String> databaseSpecificLimitBeforeStatements = new EnumMap<DatabaseType, String>(
            DatabaseType.class);
    /**
     * 特殊的数据库后缀限制声明
     */
    public static final Map<DatabaseType, String> databaseSpecificLimitAfterStatements = new EnumMap<DatabaseType, String>(
            DatabaseType.class);

    private DbSqlFactory() {

    }

    static {
        // MYSQL
        databaseSpecificLimitBeforeStatements.put(DatabaseType.MYSQL, "");
        databaseSpecificLimitAfterStatements.put(DatabaseType.MYSQL, "LIMIT #{maxResults} OFFSET #{firstResult}");

        // DB2
        databaseSpecificLimitBeforeStatements.put(DatabaseType.DB2,
                "SELECT SUB.* FROM ( select RES.* , row_number() over(order by ${orderBy}) as rnk FROM (");
        databaseSpecificLimitAfterStatements.put(DatabaseType.DB2,
                ")RES ) SUB WHERE SUB.rnk >= #{firstRow} AND SUB.rnk < #{lastRow}");
    }

    /**
     * 获取特殊的数据库前置限制声明
     * @param type 数据库类型
     * @return
     */
    public static String getDatabaseSpecificLimitBeforeStatements(DatabaseType type) {
        return StringUtilities.trimToEmpty(databaseSpecificLimitBeforeStatements.get(type));
    }

    /**
     * 获取特殊的数据库后缀限制声明
     * @param type 数据库类型
     * @return
     */
    public static String getDatabaseSpecificLimitAfterStatements(DatabaseType type) {
        return StringUtilities.trimToEmpty(databaseSpecificLimitAfterStatements.get(type));
    }

}
