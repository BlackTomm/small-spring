package com.code.springframework.beans;

/**
 * Description:
 * Create by blacktom on 2021/08/14
 **/
public class BeansException extends RuntimeException {
	public BeansException(String msg) {
		super(msg);
	}

	public BeansException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
