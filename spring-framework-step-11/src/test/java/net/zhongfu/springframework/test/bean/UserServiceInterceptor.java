package net.zhongfu.springframework.test.bean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UserServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("------------自定义方法拦截器执行开始------------");
        long start = System.currentTimeMillis();
        try {
            // 相当于执行被代理对象的方法
            return invocation.proceed();
        } finally {
            System.out.println("监控 - Begin By AOP");
            System.out.println("方法名称：" + invocation.getMethod());
            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("监控 - End");
            System.out.println("------------自定义方法拦截器执行结束------------\r\n");
        }
    }

}
