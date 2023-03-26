package net.zhongfu.springframework.jdbc.core;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 对JDBC语句进行操作的代码的通用回调接口。
 */
public interface StatementCallback<T> {

    T doInStatement(Statement statement) throws SQLException;

}
