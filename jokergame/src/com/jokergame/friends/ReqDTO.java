package com.jokergame.friends;

import java.sql.Date;

public class ReqDTO { //ģ�� ��û ���� DTO
	private int id;
	private String member1Id; //��û �޴»�� id
	private String member2Id; //��û �����»�� id
	private int status; //0: ��������� 1: ���� 2:����
	private Date date;
	
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
