package com.code.springframework.beans;

/**
 * Description:
 * Create by blacktom on 2021/08/15
 **/
public class UserService {

	private String uId;

	private UserDao userDao;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void queryUserInfo() {
		System.out.println("查询用户信息：" + userDao.queryUserName(uId));
	}
}
