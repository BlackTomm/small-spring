package com.code.springframework.beans.factory.cglib.filter;

import com.code.springframework.beans.factory.cglib.TargetObject;
import com.code.springframework.beans.factory.cglib.interceptor.TargetInterceptor;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class CglibDemoWithMethodCallbackFilter {


	/**
	 * (1)callback1：方法拦截器
	 * (2)NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
	 * (3)FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
	 */

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(TargetObject.class);

		CallbackFilter callbackFilter = new TargetMethodCallbackFilter();

		Callback noopCb = NoOp.INSTANCE;
		Callback callback1 = new TargetInterceptor();
		Callback fixedValue = new TargetResultFixed();

		//根据 filter accept()返回值确定调用的 callback
		Callback[] callbackArray = new Callback[]{noopCb, callback1, fixedValue,noopCb};

//		enhancer.setCallback(new TargetInterceptor());
		enhancer.setCallbacks(callbackArray);
		enhancer.setCallbackFilter(callbackFilter);
		TargetObject targetObject = (TargetObject) enhancer.create();

		System.out.println(targetObject);
		System.out.println(targetObject.method1("mmm1"));
		System.out.println(targetObject.method1("100"));

		System.out.println(targetObject.method2(200));
		System.out.println(targetObject.method3(300));
		System.out.println(targetObject.method3(400));
	}
}
