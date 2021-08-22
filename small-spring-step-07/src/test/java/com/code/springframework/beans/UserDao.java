package com.code.springframework.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Create by blacktom on 2021/08/22
 **/
public class UserDao {

	private static Map<String, String> hashMap = new HashMap<>();

	static {
		hashMap.put("10001", "jim");
		hashMap.put("10002", "八杯水");
		hashMap.put("10003", "阿毛");
	}

	public void initDataMethod() {
		System.out.println("执行：init-method");
	}

	public void destroyDataMethod(){
		System.out.println("执行：destroy-method");
//		hashMap.clear();
	}

	public String queryUserName(String uId) {
		return hashMap.get(uId);
	}
}
