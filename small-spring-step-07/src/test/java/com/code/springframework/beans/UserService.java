package com.code.springframework.beans;

import com.code.springframework.beans.factory.DisposableBean;
import com.code.springframework.beans.factory.InitializingBean;

/**
 * Description:
 * Create by blacktom on 2021/08/22
 **/
public class UserService implements InitializingBean, DisposableBean {

	private String uId;

	private String company;

	private String location;

	private UserDao userDao;

	@Override
	public void destroy() throws Exception {
		System.out.println("执行：UserService.destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("执行：UserService.afterPropertiesSet");
	}

	public String queryUserInfo() {
		return userDao.queryUserName(uId) + "," + company + "," + location;
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
