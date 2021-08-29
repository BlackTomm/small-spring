package com.code.springframework;

import com.code.springframework.context.support.ClassPathXmlApplicationContext;
import com.code.springframework.event.CustomEvent;
import org.junit.Test;

/**
 * Description:
 * Create by blacktom on 2021/08/29
 */
public class ApiTest {
	@Test
	public void test_event() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

		applicationContext.registerShutdownHook();
	}
}
