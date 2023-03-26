package net.zhongfu.springframework.jdbc.support;

import cn.hutool.core.lang.Assert;
import net.zhongfu.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;

/**
 * 用于{@LinkJDBC和其他访问cn.bugstack.springframework.jdbc.core.JdbcTemplate}的
 * DAO助手的基类，定义常见属性，如数据源和异常翻译器。
 */
public abstract class JdbcAccessor implements InitializingBean {

    private DataSource dataSource;

    /**
     * Set the JDBC DataSource to obtain connections from.
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Return the DataSource used by this template.
     */
    public DataSource getDataSource() {
        return this.dataSource;
    }

    /**
     * Obtain the DataSource for actual use.
     * @return the DataSource (never {@code null})
     * @throws IllegalStateException in case of no DataSource set
     * @since 5.0
     */
    protected DataSource obtainDataSource() {
        DataSource dataSource = getDataSource();
        Assert.state(dataSource != null, "No DataSource set");
        return dataSource;
    }

    /**
     * Eagerly initialize the exception translator, if demanded,
     * creating a default one for the specified DataSource if none set.
     */
    @Override
    public void afterPropertiesSet() {
        if (getDataSource() == null) {
            throw new IllegalArgumentException("Property 'dataSource' is required");
        }
    }

}
