package net.zhongfu.springframework.beans.factory.support;

import net.zhongfu.springframework.beans.BeansException;
import net.zhongfu.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>接口描述: 对象实例化策略接口</p>
 * <p>创建时间: 2022-11-24-19:30 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @update [1][2022-11-24-19:30] [李遵奇][变更描述:及时维护注释]
 */
public interface InstantiationStrategy {

    /**
     * 实例化接口
     *
     * @param beanDefinition bean 对象
     * @param beanName       bean 名称
     * @param ctor           作用：获取与入参信息相对应的构造函数
     * @param args           参数数组
     * @return java.lang.Object
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
