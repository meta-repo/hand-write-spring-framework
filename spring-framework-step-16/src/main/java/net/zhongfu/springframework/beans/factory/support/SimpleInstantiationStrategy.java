package net.zhongfu.springframework.beans.factory.support;

import net.zhongfu.springframework.beans.BeansException;
import net.zhongfu.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 基于 jdk 实现构造函数实例化策略</p>
 * <p>创建时间: 2022-11-24-19:35 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @create [1][2022-11-24-19:35] [李遵奇][变更描述:及时维护注释]
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != ctor) {
                Class[] parameterTypes = ctor.getParameterTypes();
                Constructor declaredConstructor = clazz.getDeclaredConstructor(parameterTypes);
                return declaredConstructor.newInstance(args);
            } else {
                Constructor declaredConstructor = clazz.getDeclaredConstructor();
                return declaredConstructor.newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}