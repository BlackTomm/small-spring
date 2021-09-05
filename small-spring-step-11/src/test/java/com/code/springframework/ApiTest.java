package com.code.springframework;

import com.code.springframework.aop.AdvisedSupport;
import com.code.springframework.aop.TargetSource;
import com.code.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.code.springframework.aop.framework.Cglib2AopProxy;
import com.code.springframework.aop.framework.JdkDynamicAopProxy;
import com.code.springframework.beans.IUserService;
import com.code.springframework.beans.UserService;
import com.code.springframework.beans.UserServiceInterceptor;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Description:
 * Create by blacktom on 2021/09/05
 */
public class ApiTest {

	@Test
	public void aopTest() throws NoSuchMethodException {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(
				"execution(* com.code.springframework.beans.UserService.*(..))");

		Class<UserService> clazz = UserService.class;
		Method method = clazz.getDeclaredMethod("queryUserInfo");

		System.out.println(pointcut.matches(clazz));
		System.out.println(pointcut.matches(method,clazz));
	}

	@Test
	public void dynamicTest() {
		IUserService userService = new UserService();
		// 组装代理对象
		AdvisedSupport advised = new AdvisedSupport();
		advised.setTargetSource(new TargetSource(userService));
		advised.setMethodInterceptor(new UserServiceInterceptor());
		advised.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.code.springframework.beans.IUserService.*(..))"));

		// 代理对象(JdkDynamicAopProxy)
		IUserService jdkProxy = (IUserService) new JdkDynamicAopProxy(advised).getProxy();
		// 测试调用
		System.out.println("测试结果：" + jdkProxy.queryUserInfo());
//		System.out.println("测试结果："+jdkProxy.register("jim_proxy"));

		System.out.println("==============================");


		// 代理对象(Cglib2AopProxy)
		IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advised).getProxy();
		// 测试调用
		System.out.println("测试结果：" + proxy_cglib.register("花花"));
	}
}
