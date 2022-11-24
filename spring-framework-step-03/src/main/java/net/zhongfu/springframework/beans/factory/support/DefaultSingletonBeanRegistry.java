package net.zhongfu.springframework.beans.factory.support;

import net.zhongfu.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 默认通用单例对象实现 </p>
 * <p>创建时间: 2022-11-23-21:29 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @create [1][2022-11-23-21:29] [李遵奇][创建描述:implements SingletonBeanRegistry]
 * +getSingleton(String beanName)
 * +registerSingleton(String beanName, Object singletonObject)
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
