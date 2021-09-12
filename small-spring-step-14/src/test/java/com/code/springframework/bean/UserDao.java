package com.code.springframework.bean;

import com.code.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Create by blacktom on 2021/09/12
 */
@Component
public class UserDao {
	private static Map<String, String> hashMap = new HashMap<>();

	static {
		hashMap.put("10001", "jim，北京，亦庄");
		hashMap.put("10002", "八杯水，上海，尖沙咀");
		hashMap.put("10003", "阿毛，香港，铜锣湾");
	}

	public String queryUserName(String uId) {
		return hashMap.get(uId);
	}
}
