package com.hewen.utils;

public class ReadQuery {

    /**
     * 检查 Cypher 查询是否只包含读操作。
     * @param query Cypher 查询语句。
     * @return 如果是读操作返回 true，否则返回 false。
     */
    public static boolean isReadQuery(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return !(lowerCaseQuery.contains("create") || lowerCaseQuery.contains("merge") ||
                lowerCaseQuery.contains("set") || lowerCaseQuery.contains("delete") ||
                lowerCaseQuery.contains("remove"));
    }
}
