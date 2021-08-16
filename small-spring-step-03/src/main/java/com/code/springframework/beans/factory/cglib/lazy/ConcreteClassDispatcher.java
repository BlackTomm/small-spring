package com.code.springframework.beans.factory.cglib.lazy;

import com.code.springframework.beans.factory.cglib.TargetObject;
import net.sf.cglib.proxy.Dispatcher;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class ConcreteClassDispatcher implements Dispatcher {

	@Override
	public Object loadObject() throws Exception {
		System.out.println("before Dispatcher...");
		PropertyBean propertyBean = new PropertyBean();
		propertyBean.setKey("xxx");
		propertyBean.setValue(new TargetObject());
		System.out.println("after Dispatcher...");
		return propertyBean;
	}
}
