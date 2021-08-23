package com.code.springframework;

import com.code.springframework.beans.UserService;
import com.code.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * Description:
 * Create by blacktom on 2021/08/22
 **/
public class ApiTest {

	@Test
	public void xmlTest() {
		// 1.初始化 BeanFactory
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		applicationContext.registerShutdownHook();

		// 2. 获取Bean对象调用方法
		UserService userService = applicationContext.getBean("userService", UserService.class);
		String result = userService.queryUserInfo();
		System.out.println("测试结果：" + result);
	}

	@Test
	public void hookTest() {
		Runtime.getRuntime().addShutdownHook(new Thread(()->System.out.println("close hook!")));
	}
}
