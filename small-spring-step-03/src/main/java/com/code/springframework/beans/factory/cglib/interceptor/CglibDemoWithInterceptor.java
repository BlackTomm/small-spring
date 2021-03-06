package com.code.springframework.beans.factory.cglib.interceptor;

import com.code.springframework.beans.factory.cglib.TargetObject;
import net.sf.cglib.proxy.Enhancer;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class CglibDemoWithInterceptor {
	public static void main(String[] args) {
		Enhancer enhancer =new Enhancer();

		enhancer.setSuperclass(TargetObject.class);
		enhancer.setCallback(new TargetInterceptor());
		TargetObject targetObject2=(TargetObject)enhancer.create();

		System.out.println(targetObject2);

		System.out.println(targetObject2.method1("mmm1"));
		System.out.println(targetObject2.method2(100));
		System.out.println(targetObject2.method3(200));
	}
}
