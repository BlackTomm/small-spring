package com.code.springframework.aop.framework.adapter;

import com.code.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Description:
 * Create by blacktom on 2021/09/06
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
	private MethodBeforeAdvice advice;

	public MethodBeforeAdviceInterceptor() {
	}

	public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
		this.advice = advice;
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(),methodInvocation.getThis());
		return methodInvocation.proceed();
	}

	public MethodBeforeAdvice getAdvice() {
		return advice;
	}

	public void setAdvice(MethodBeforeAdvice advice) {
		this.advice = advice;
	}
}