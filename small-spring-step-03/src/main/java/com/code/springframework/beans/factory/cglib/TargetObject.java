package com.code.springframework.beans.factory.cglib;

/**
 * Description: CGLIB(Code Generation Library) 介绍与原理
 * https://www.runoob.com/w3cnote/cglibcode-generation-library-intro.html
 * Create by blacktom on 2021/08/14
 **/
public class TargetObject {
	public String method1(String paramName) {
		return paramName;
	}

	public int method2(int count) {
		return count;
	}

	public int method3(int count) {
		return count;
	}

	@Override
	public String toString() {
		return "TargetObject []"+ getClass();
	}
}
