package com.code.springframework.beans.factory.cglib.lazy;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class LazyDemo {
	public static void main(String[] args) {
		LazyBean lazyBean = new LazyBean("jim", 20);
		for (int i = 0; i < 3; i++) {
			System.out.println("PropertyBean-"+i+" : "+lazyBean.getPropertyBean());
			System.out.println("PropertyBeanDispatcher-"+i+" : "+lazyBean.getPropertyBeanDispatcher());
		}
	}
}
