package net.zhongfu.springframework.context;

import java.util.EventListener;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>接口描述: </p>
 * <p>创建时间: 2022-11-27-23:21 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-27-23:21] [李遵奇][创建描述:及时维护注释]
 * 第10章新增
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);

}
