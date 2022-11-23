package net.zhongfu.springframework.beans.factory.support;

import net.zhongfu.springframework.beans.BeansException;
import net.zhongfu.springframework.beans.factory.config.BeanDefinition;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 自动创建 bean 抽象工厂类</p>
 * <p>创建时间: 2022-11-23-22:28 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @create [1][2022-11-23-22:28] [李遵奇][创建描述:及时维护注释]
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            // 获取class，并实例化
            Class beanClass = beanDefinition.getBeanClass();
            bean = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 注册到单例，做缓存，方便下次使用
        registerSingleton(beanName, bean);

        return bean;
    }
}
