package net.zhongfu.springframework.beans.factory.config;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 定义单例 Bean 注册接口</p>
 * <p>创建时间: 2022-11-23-21:08 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @create [1][2022-11-23-21:08] [李遵奇][创建描述:单例 Bean 注册接口]
 * @create [2][2022-11-23-21:08] [李遵奇][创建描述:定义获取单例对象的接口]
 * @create [3][2022-11-23-21:08] [李遵奇][创建描述:定义注册单例对象的接口]
 * +getSingleton(String beanName)
 * +registerSingleton(String beanName, Object singletonObject)
 */
public interface SingletonBeanRegistry {
    /**
     * 返回在给定名称下注册的（原始）单例对象。
     *
     * @param beanName 要查找的bean的名称
     * @return 返回注册的单例对象
     */
    Object getSingleton(String beanName);

    /**
     * 注册单例对象
     *
     * @param beanName        Bean 对象名称
     * @param singletonObject Bean 对象
     */
    void registerSingleton(String beanName, Object singletonObject);
}
