package net.zhongfu.springframework.aop.framework.autoproxy;


import net.zhongfu.springframework.aop.*;
import net.zhongfu.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import net.zhongfu.springframework.aop.framework.ProxyFactory;
import net.zhongfu.springframework.beans.BeansException;
import net.zhongfu.springframework.beans.factory.BeanFactory;
import net.zhongfu.springframework.beans.factory.BeanFactoryAware;
import net.zhongfu.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import net.zhongfu.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: </p>
 * <p>创建时间: 2022-11-29-11:08 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-29-11:08] [李遵奇][创建描述:及时维护注释]
 *
 * 融入 Bean 生命周期的自动代理创建者
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // 基础设施类
        if (isInfrastructureClass(beanClass)) {
            return null;
        }

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        // 遍历advisors 填充属性信息（如目标对象、拦截方法、匹配器）然后返回代理对象
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) {
                continue;
            }

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try {
                // 被代理的目标对象封装
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
                // 被代理的目标对象
                advisedSupport.setTargetSource(targetSource);
                // 方法拦截器
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                // 方法匹配器(检查目标方法是否符合通知条件)
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                // 是否启动cglib代理
                advisedSupport.setProxyTargetClass(false);
            // 选用代理方式
            return new ProxyFactory(advisedSupport).getProxy();

        }

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}

