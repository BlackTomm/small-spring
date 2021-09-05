package com.code.springframework.beans;

import java.util.Random;

/**
 * Description:
 * Create by blacktom on 2021/08/22
 **/
public class UserService implements IUserService {

	private String uId;

	private String company;

	private String location;

	private UserDao userDao;

	public String queryUserInfo() {
		try {
			Thread.sleep(new Random(1).nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "jim，100001，深圳";
//		return userDao.queryUserName(uId) + "," + company + "," + location;
	}

	@Override
	public String register(String userName) {
		try {
			Thread.sleep(new Random(1).nextInt(100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "注册用户：" + userName + " success！";
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
