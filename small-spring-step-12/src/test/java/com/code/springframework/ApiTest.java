package com.code.springframework;

import com.code.springframework.aop.AdvisedSupport;
import com.code.springframework.aop.TargetSource;
import com.code.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.code.springframework.aop.framework.ProxyFactory;
import com.code.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.code.springframework.beans.IUserService;
import com.code.springframework.beans.UserService;
import com.code.springframework.beans.UserServiceBeforeAdvice;
import com.code.springframework.beans.UserServiceInterceptor;
import com.code.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Before;
import org.junit.Test;

/**
 * Description:
 * Create by blacktom on 2021/09/05
 */
public class ApiTest {

	private AdvisedSupport advisedSupport;

	@Before
	public void init() {
		// 目标对象
		IUserService userService = new UserService();
		// 组装代理信息
		advisedSupport = new AdvisedSupport();
		advisedSupport.setTargetSource(new TargetSource(userService));
		advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
		advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.code.springframework.beans.IUserService.*(..))"));
	}

	@Test
	public void test_proxyFactory() {
		advisedSupport.setProxyTargetClass(false); // false/true，JDK动态代理、CGlib动态代理
		IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();

		System.out.println("测试结果：" + proxy.queryUserInfo());
	}


	@Test
	public void test_beforeAdvice() {
		UserServiceBeforeAdvice beforeAdvice = new UserServiceBeforeAdvice();
		MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(beforeAdvice);
		advisedSupport.setMethodInterceptor(interceptor);

		IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();
		System.out.println("测试结果：" + proxy.queryUserInfo());
	}

	@Test
	public void aopTest() throws NoSuchMethodException {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

		IUserService userService = applicationContext.getBean("userService", IUserService.class);
		System.out.println("测试结果：" + userService.queryUserInfo());
	}


}
