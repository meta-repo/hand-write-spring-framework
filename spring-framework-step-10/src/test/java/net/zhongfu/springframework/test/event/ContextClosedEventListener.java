package net.zhongfu.springframework.test.event;

import net.zhongfu.springframework.context.ApplicationListener;
import net.zhongfu.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
