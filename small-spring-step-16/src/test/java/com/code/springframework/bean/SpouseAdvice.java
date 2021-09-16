package com.code.springframework.bean;

import com.code.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Description:
 * Create by blacktom on 2021/09/16
 */
public class SpouseAdvice implements MethodBeforeAdvice {
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("关怀小两口(切面)：" + method);
	}
}
