package org.bpm.db.persistence.operate;

import java.util.HashMap;
import java.util.Map;

/**
 * 排序方向
 */
public class Direction {

    private static final Map<String, Direction> directions = new HashMap<String, Direction>();

    /**
     * 升序
     */
    public static final Direction ASCENDING = new Direction("asc");
    /**
     * 降序
     */
    public static final Direction DESCENDING = new Direction("desc");

    private String name;

    public Direction(String name) {
        this.name = name;
        directions.put(name, this);
    }

    public String getName() {
        return name;
    }

    /**
     * 按排序名获取数据
     * @param directionName 排序名
     * @return
     */
    public static Direction findByName(String directionName) {
        return directions.get(directionName);
    }
}
