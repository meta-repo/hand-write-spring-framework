package net.zhongfu.springframework.aop.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.zhongfu.springframework.aop.AdvisedSupport;

import java.lang.reflect.Method;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: </p>
 * <p>创建时间: 2022-11-28-15:37 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-28-15:37] [李遵奇][创建描述:及时维护注释]
 */
public class Cglib2AopProxy implements AopProxy {

    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        // 实例化一个Enhancer增强剂类
        Enhancer enhancer = new Enhancer();
        // 设置超类
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        // 设置接口
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        // 回调函数，调用被代理对象的方法时，回调这个
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        // 创建
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, objects, methodProxy);
            // 如果符合匹配，调用自定义拦截器实现的方法
            if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
                // 获取拦截器，调用其实现的方法。
                return advised.getMethodInterceptor().invoke(methodInvocation);
            }
            // 否则反射，调用被代理对象，要执行的方法
            return methodInvocation.proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
            super(target, method, arguments);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }

    }

}

