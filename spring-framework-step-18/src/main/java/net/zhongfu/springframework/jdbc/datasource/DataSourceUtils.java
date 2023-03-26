package net.zhongfu.springframework.jdbc.datasource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Helper类，提供用于从{@link javax.sql.DataSource}获取JDBC连接的静态方法。
 */
public abstract class DataSourceUtils {

    /**
     * 从给定的数据源获取连接。
     */
    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to obtain JDBC Connection", e);
        }
    }

}
