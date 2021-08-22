package com.code.springframework.core.io;

/**
 * Description:
 * Create by blacktom on 2021/08/15
 **/
public interface ResourceLoader {

	/**
	 * Pseudo URL prefix for loading from the class path: "classpath:"
	 */
	String CLASSPATH_URL_PREFIX = "classpath:";

	Resource getResource(String location);
}
