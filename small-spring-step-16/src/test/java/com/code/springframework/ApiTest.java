package com.code.springframework;

import com.code.springframework.bean.Husband;
import com.code.springframework.bean.Wife;
import com.code.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * Description:
 * Create by blacktom on 2021/09/16
 */
public class ApiTest {
	@Test
	public void test_circular() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		Husband husband = applicationContext.getBean("husband", Husband.class);
		Wife wife = applicationContext.getBean("wife", Wife.class);
		System.out.println("老公的媳妇：" + husband.queryWife());
		System.out.println("媳妇的老公：" + wife.queryHusband());
	}
}
