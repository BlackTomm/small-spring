package com.code.springframework.beans;

import com.code.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Description:
 * Create by blacktom on 2021/09/06
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("拦截方法：" + method.getName());
	}

}
