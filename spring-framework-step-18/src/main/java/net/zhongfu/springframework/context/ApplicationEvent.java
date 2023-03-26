package net.zhongfu.springframework.context;

import java.util.EventObject;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>类描述: </p>
 * <p>创建时间: 2022-11-27-23:21 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-27-23:21] [李遵奇][创建描述:及时维护注释]
 * 第10章新增
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * 构建了一个原型事件。Constructs a prototypical Event.
     *
     * @param source 最初在其上发生事件的对象。 The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }

}
