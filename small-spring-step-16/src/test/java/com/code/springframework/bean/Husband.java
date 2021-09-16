package com.code.springframework.bean;

/**
 * Description:
 * Create by blacktom on 2021/09/16
 */
public class Husband {

	private Wife wife;

	public String queryWife(){
		return "Husband.wife";
	}

	public Wife getWife() {
		return wife;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
	}
}
