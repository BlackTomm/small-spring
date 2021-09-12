package com.code.springframework;

import com.code.springframework.bean.IUserService;
import com.code.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * Description:
 * Create by blacktom on 2021/09/12
 */
public class ApiTest {

	@Test
	public void test_scan() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		IUserService userService = applicationContext.getBean("userService", IUserService.class);
		System.out.println("测试结果：" + userService.queryUserInfo());
	}
}
