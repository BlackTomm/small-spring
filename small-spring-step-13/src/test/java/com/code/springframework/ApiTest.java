package com.code.springframework;

import com.code.springframework.bean.IUserService;
import com.code.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * Description:
 * Create by blacktom on 2021/09/12
 */
public class ApiTest {

	/**
	 * Default placeholder prefix: {@value}
	 */
	public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

	/**
	 * Default placeholder suffix: {@value}
	 */
	public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

	@Test
	public void stringIndexOf() {
		String strVal = "${blacktom}";
		StringBuilder buffer = new StringBuilder(strVal);
		int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
		int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
		String propVal = "pro-value";
		buffer.replace(startIdx, stopIdx + 1, propVal);
	}

	@Test
	public void scanTest() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
		IUserService userService = applicationContext.getBean("userService", IUserService.class);
		System.out.println("测试结果：" + userService.queryUserInfo());
	}

	@Test
	public void propertyTest() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
		IUserService userService = applicationContext.getBean("userService", IUserService.class);
		System.out.println("测试结果：" + userService);
	}

}
