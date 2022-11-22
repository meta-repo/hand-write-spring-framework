package net.zhongfu.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: Bean 工厂</p>
 * <p>创建时间: 2022-11-22-22:31 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-22] [李遵奇][创建描述:定义工厂类 BeanFactory]
 */
public class BeanFactory {
    /**
     * 封装一个 HashMap 用于存储 Bean 对象的定义 BeanDefinition
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 提供一个通过 name 从 HashMap 获取（ Bean 对象的定义 BeanDefinition ）的方法
     */
    public Object getBean(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        return beanDefinition.getBean();
    }

    /**
     * 提供一个向 HashMap 中，注册（ Bean 对象的定义 BeanDefinition ）的方法，key 为 name
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
