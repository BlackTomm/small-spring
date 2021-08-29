package com.code.springframework.event;

import com.code.springframework.context.ApplicationListener;
import com.code.springframework.context.event.ContextClosedEvent;

/**
 * Description:
 * Create by blacktom on 2021/08/29
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println("关闭事件：" + this.getClass().getName());
	}

}