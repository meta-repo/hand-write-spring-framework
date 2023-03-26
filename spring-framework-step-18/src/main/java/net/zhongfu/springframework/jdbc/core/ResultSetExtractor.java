package net.zhongfu.springframework.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link JdbcTemplate}的查询方法使用的回调接口。
 */
public interface ResultSetExtractor<T> {

    T extractData(ResultSet rs) throws SQLException;

}
