package net.zhongfu.springframework.aop;

import java.lang.reflect.Method;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>接口描述: </p>
 * <p>创建时间: 2022-11-29-10:57 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-29-10:57] [李遵奇][创建描述:及时维护注释]
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    /**
     * 在调用给定方法之前进行回调。 Callback before a given method is invoked.
     * 用户实现此方法
     *
     * @param method 正在调用的方法 method being invoked
     * @param args   方法的参数 arguments to the method
     * @param target 方法调用的目标。可能为<code>空</code>。 target of the method invocation. May be <code>null</code>.
     * @throws Throwable if this object wishes to abort the call.
     *                   Any exception thrown will be returned to the caller if it's
     *                   allowed by the method signature. Otherwise the exception
     *                   will be wrapped as a runtime exception.
     */
    void before(Method method, Object[] args, Object target) throws Throwable;

}
