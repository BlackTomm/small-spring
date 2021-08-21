package com.code.springframework;

import com.code.springframework.beans.UserService;
import com.code.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.code.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.code.springframework.common.SelfBeanFactoryPostProcessor;
import com.code.springframework.common.SelfBeanPostProcessor;
import com.code.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.util.Arrays;

/**
 * Description:
 * Create by blacktom on 2021/08/15
 **/
public class ApiTest {

	@Test
	public void xmlTest() {
		// 1.初始化 BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2. 读取配置文件&注册Bean
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions("classpath:spring.xml");

		// 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
		SelfBeanFactoryPostProcessor beanFactoryPostProcessor = new SelfBeanFactoryPostProcessor();
		beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

		// 4. Bean实例化之后，修改 Bean 属性信息
		SelfBeanPostProcessor beanPostProcessor = new SelfBeanPostProcessor();
		beanFactory.addBeanPostProcessor(beanPostProcessor);

		//获取所有 bean
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		Arrays.stream(beanDefinitionNames).forEach(System.out::println);

		// 5. 获取Bean对象调用方法
		UserService userService = beanFactory.getBean("userService", UserService.class);
		String result = userService.queryUserInfo();
		System.out.println("测试结果：" + result);
	}

	@Test
	public void xmlPostTest() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

		UserService userService = applicationContext.getBean("userService", UserService.class);
		String result = userService.queryUserInfo();
		System.out.println("测试结果：" + result);
	}
}
