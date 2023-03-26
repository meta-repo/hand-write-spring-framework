package net.zhongfu.springframework.beans.factory;

import net.zhongfu.springframework.beans.BeansException;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>接口描述: 定义 Bean 工厂接口</p>
 * <p>创建时间: 2022-11-23-20:53 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @create [1][2022-11-23-20:53] [李遵奇][创建描述:定义 Bean 工厂接口]
 * @create [1][2022-11-23-20:53] [李遵奇][创建描述:定义获取 bean 的接口]
 */
public interface BeanFactory {
    /**
     * 返回 Bean 的实例对象
     *
     * @param name 要检索的bean的名称
     * @return 实例化的 Bean 对象
     * @throws BeansException 不能获取 Bean 对象，则抛出异常
     */
    Object getBean(String name) throws BeansException;

    /**
     * 返回含构造函数的 Bean 实例对象
     * @param name 要检索的bean的名称
     * @param args 构造函数入参，可能有多个参数，这里使用可变参数
     * @return 实例化的 Bean 对象
     * @throws BeansException 不能获取 Bean 对象，则抛出异常
     */
    Object getBean(String name, Object... args) throws BeansException;


    /**
     * 第5章新增
     *
     * 返回指定泛型的对象
     * @param name  要检索的bean的名称
     * @param requiredType 类型
     * @param <T>          泛型
     * @return             实例化的 Bean 对象
     * @throws BeansException 不能获取 Bean 对象，则抛出异常
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    /**
     * 第14章新增
     * 返回指定泛型的对象
     * @param requiredType 类型
     * @param <T>          泛型
     * @return             实例化的 Bean 对象
     * @throws BeansException 不能获取 Bean 对象，则抛出异常
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;
}
