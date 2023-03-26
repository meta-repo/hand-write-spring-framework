package net.zhongfu.springframework.jdbc.core;

/**
 * 由可以提供SQL字符串的对象实现的接口。
 */
public interface SqlProvider {

    String getSql();

}
