package net.zhongfu.springframework.util;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>接口描述: 定义解析字符串接口</p>
 * <p>创建时间: 2022-11-29-15:16 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-29-15:16] [李遵奇][创建描述:及时维护注释]
 *
 *
 * 由 PlaceholderResolvingStringValueResolver 类实现并完成属性值的获取操作
 */
public interface StringValueResolver {

    String resolveStringValue(String strVal);

}
