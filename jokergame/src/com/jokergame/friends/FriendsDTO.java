package com.jokergame.friends;

import java.sql.Date;

public class FriendsDTO {
	private int id;
	private String member1Id;
	private String member2Id;
	private Date requestDate;
	private Date sureDate;
	private int state;
	
	//getters and setters
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMember1Id() {
		return member1Id;
	}
	public void setMember1Id(String member1Id) {
		this.member1Id = member1Id;
	}
	public String getMember2Id() {
		return member2Id;
	}
	public void setMember2Id(String member2Id) {
		this.member2Id = member2Id;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getSureDate() {
		return sureDate;
	}
	public void setSureDate(Date sureDate) {
		this.sureDate = sureDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
}
