package net.zhongfu.springframework.jdbc.core;

import java.util.List;
import java.util.Map;

/**
 * 接口，指定一组基本的JDBC操作。
 */
public interface JdbcOperations {

    <T> T execute(StatementCallback<T> action) throws Exception;

    void execute(String sql);

    <T> T query(String sql, ResultSetExtractor<T> res);

    <T> List<T> query(String sql, RowMapper<T> rowMapper);

    List<Map<String, Object>> queryForList(String sql);

}
