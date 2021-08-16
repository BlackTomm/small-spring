package com.code.springframework.beans.factory.cglib.interceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description: 目标对象拦截器，实现MethodInterceptor
 * Create by blacktom on 2021/08/14
 **/
public class TargetInterceptor implements MethodInterceptor {
/*
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("调用前 method: " + method);
		Object result = methodProxy.invoke(o, objects);
		System.out.println("调用后 result：" + result);
		return result;
	}*/

	/**
	 * 重写方法拦截在方法前和方法后加入业务
	 * Object obj为目标对象
	 * Method method为目标方法
	 * Object[] params 为参数，
	 * MethodProxy proxy CGlib方法代理对象
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] params,
							MethodProxy proxy) throws Throwable {
		System.out.println("调用前 method: " + method);
		Object result = proxy.invokeSuper(obj, params);
		System.out.println("调用后 result：" + result);

		return result;
	}
}
