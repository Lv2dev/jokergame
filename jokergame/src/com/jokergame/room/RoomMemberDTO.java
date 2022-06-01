package com.jokergame.room;

public class RoomMemberDTO {
	private int id;
	private int room_id;
	private int member_id;
	private int bet_exp;
	private int ready;
	
	//getters and setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getBet_exp() {
		return bet_exp;
	}
	public void setBet_exp(int bet_exp) {
		this.bet_exp = bet_exp;
	}
	public int getReady() {
		return ready;
	}
	public void setReady(int ready) {
		this.ready = ready;
	}
	
}
