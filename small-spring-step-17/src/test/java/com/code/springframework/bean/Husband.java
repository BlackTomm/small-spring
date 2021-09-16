package com.code.springframework.bean;

import java.util.Date;

/**
 * Description:
 * Create by blacktom on 2021/09/17
 */
public class Husband {

	private String wifeName;

	private Date marriageDate;

	public String getWifeName() {
		return wifeName;
	}

	public void setWifeName(String wifeName) {
		this.wifeName = wifeName;
	}

	public Date getMarriageDate() {
		return marriageDate;
	}

	public void setMarriageDate(Date marriageDate) {
		this.marriageDate = marriageDate;
	}

	@Override
	public String toString() {
		return "Husband{" +
				"wifeName='" + wifeName + '\'' +
				", marriageDate=" + marriageDate +
				'}';
	}
}
