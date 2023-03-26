package net.zhongfu.springframework.aop.framework.adapter;

import net.zhongfu.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 围绕 Advice 做一个类似拦截器的链路</p>
 * <p>创建时间: 2022-11-29-11:08 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-29-11:08] [李遵奇][创建描述:及时维护注释]
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {
    }

    /**
     * 拦截器注入
     * @param advice
     */
    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 调用用户实现的拦截器方法
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        //
        return methodInvocation.proceed();
    }

}

