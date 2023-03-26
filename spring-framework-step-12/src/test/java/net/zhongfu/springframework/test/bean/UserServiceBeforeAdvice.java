package net.zhongfu.springframework.test.bean;

import net.zhongfu.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    /**
     * 自定义拦截方法--环绕拦截
     *
     * @param method 正在调用的方法 method being invoked
     * @param args   方法的参数 arguments to the method
     * @param target 方法调用的目标。可能为<code>空</code>。 target of the method invocation. May be <code>null</code>.
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }

}
