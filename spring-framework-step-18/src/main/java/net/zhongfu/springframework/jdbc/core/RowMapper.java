package net.zhongfu.springframework.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 由{@link JdbcTemplate}用来映射。
 * 以每行为基础的{@link java.sql.ResultSet}。
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs, int rowNum) throws SQLException;

}
