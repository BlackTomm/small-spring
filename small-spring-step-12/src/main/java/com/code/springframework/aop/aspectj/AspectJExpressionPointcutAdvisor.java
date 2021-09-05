package com.code.springframework.aop.aspectj;

import com.code.springframework.aop.Pointcut;
import com.code.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Description:
 * Create by blacktom on 2021/09/06
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
	// 切面
	private AspectJExpressionPointcut pointcut;
	// 具体的拦截方法
	private Advice advice;
	// 表达式
	private String expression;

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public Pointcut getPointcut() {
		if (pointcut == null) {
			pointcut= new AspectJExpressionPointcut(expression);
		}
		return pointcut;
	}

	@Override
	public Advice getAdvice() {
		return advice;
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}
}
