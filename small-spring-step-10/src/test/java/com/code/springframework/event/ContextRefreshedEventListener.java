package com.code.springframework.event;

import com.code.springframework.context.ApplicationListener;
import com.code.springframework.context.event.ContextRefreshedEvent;

/**
 * Description:
 * Create by blacktom on 2021/08/29
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("刷新事件：" + this.getClass().getName());
	}

}