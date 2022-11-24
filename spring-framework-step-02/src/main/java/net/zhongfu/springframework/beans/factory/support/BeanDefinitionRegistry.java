package net.zhongfu.springframework.beans.factory.support;

import net.zhongfu.springframework.beans.factory.config.BeanDefinition;
/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>接口描述: Bean 定义注册接口</p>
 * <p>创建时间: 2022-11-23-21:02 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @author fuzhengwei
 * @author DerekYRC
 * @version v1.0
 * @create [1][2022-11-23-21:02] [李遵奇][创建描述:定义 Bean 工厂接口]
 * @create [2][2022-11-23-21:02] [李遵奇][创建描述:定义注册 Bean 对象的方法]
 * ~void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName       Bean 名称
     * @param beanDefinition Bean 定义
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
