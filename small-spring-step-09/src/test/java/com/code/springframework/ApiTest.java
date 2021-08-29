package com.code.springframework;

import com.code.springframework.beans.HashUtil;
import com.code.springframework.beans.UserService;
import com.code.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * Description:
 * Create by blacktom on 2021/08/22
 **/
public class ApiTest {

	@Test
	public void prototypeTest() throws NoSuchFieldException, IllegalAccessException {
		// 1.初始化 BeanFactory
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		applicationContext.registerShutdownHook();

		// 2. 获取Bean对象调用方法
		UserService userService01 = applicationContext.getBean("userService", UserService.class);
		UserService userService02 = applicationContext.getBean("userService", UserService.class);
		// 3. 配置 scope="prototype/singleton"
		System.out.println(userService01);
		System.out.println(userService02);

		//没有计算HASHCODE之前的对象头
		System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
		// 4. 打印十六进制哈希，JVM 计算的hashcode
		System.out.println(userService01+" 十六进制哈希："+Integer.toHexString(userService01.hashCode()));
		HashUtil.countHash(userService01);

		System.out.println(VM.current().details());
		//当计算完hashcode之后，我们可以查看对象头的信息变化
		System.out.println(ClassLayout.parseInstance(userService01).toPrintable());

		/*IUserDao userDao = applicationContext.getBean("proxyUserDao", IUserDao.class);
		System.out.println(ClassLayout.parseInstance(userDao).toPrintable());*/
	}

	@Test
	public void factoryBeanTest() {
		// 1.初始化 BeanFactory
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		applicationContext.registerShutdownHook();
		// 2. 调用代理方法
		UserService userService = applicationContext.getBean("userService", UserService.class);
		System.out.println("测试结果：" + userService.queryUserInfo());
	}
}
