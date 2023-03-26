package net.zhongfu.springframework.context.event;

import net.zhongfu.springframework.context.ApplicationEvent;
import net.zhongfu.springframework.context.ApplicationListener;

/**
 * 模块名称: 徒手实现 Spring 框架
 * <p>接口描述: </p>
 * <p>创建时间: 2022-11-27-23:28 </p>
 * <p>公司信息: 中孚安全技术有限公司 产品研发体系/中央能力部/大数据平台能力部</p>
 *
 * @author <a href="mail to: lizunqi@zhongfu.net" rel="nofollow">李遵奇</a>
 * @version v1.0
 * @create [1][2022-11-27-23:28] [李遵奇][创建描述:及时维护注释]
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加要通知所有事件的监听程序。 Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 从通知列表中删除监听程序。Remove a listener from the notification list.
     * @param listener the listener to remove
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 将给定的应用程序事件多播到适当的监听器。Multicast the given application event to appropriate listeners.
     * @param event the event to multicast
     */
    void multicastEvent(ApplicationEvent event);

}

