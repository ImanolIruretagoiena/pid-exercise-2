package com.sda.pid_exercise_2;

public class Member {

	private String name;
	private String id;
	private boolean attended;
	
	public Member(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isAttended() {
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}
	
	public void printDetails() {
		System.out.println("Name: " + name + ", id: " + id);
	}
}
