package net.zhongfu.springframework.aop.framework;

/**
 * 模块名称: 徒手实现 Spring 框架
 * AOP 代理的抽象
 * <p>接口描述: 已配置的AOP代理的委托接口，允许创建实际的代理对象。</p>
 * <p>创建时间: 2022-11-28-15:34 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-28-15:34] [李遵奇][创建描述:及时维护注释]
 */
public interface AopProxy {

    Object getProxy();

}

