package com.code.springframework.aop;

import com.code.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.code.springframework.beans.UserService;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Description:
 * Create by blacktom on 2021/08/30
 */
public class AopTest {

	@Test
	public void aopTest() throws NoSuchMethodException {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(
				"execution(* com.code.springframework.beans.UserService.*(..))");

		Class<UserService> clazz = UserService.class;
		Method method = clazz.getDeclaredMethod("queryUserInfo");

		System.out.println(pointcut.matches(clazz));
		System.out.println(pointcut.matches(method,clazz));
	}
}
