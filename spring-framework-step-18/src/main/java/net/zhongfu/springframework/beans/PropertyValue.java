package net.zhongfu.springframework.beans;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: 属性类</p>
 * <p>创建时间: 2022-11-25-18:44 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @update [1][2022-11-25-18:44] [李遵奇][变更描述:及时维护注释]
 */
public class PropertyValue {

    /** 属性名称 */
    private final String name;

    /** 属性值 */
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }


}
