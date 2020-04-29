package com.sda.pid_exercise_2;

/**
 * Class for Member objects.
 * @author ImanolIruretagoiena
 * @version 2020.04.29
 */
public class Member {

	// Name of the member.
	private String name;
	// ID of the member.
	private String id;
	// Whether the member attended or not.
	private boolean attended;
	
	/**
	 * Constructor for Member objects.
	 * @param name Name of the member.
	 * @param id ID of the member.
	 */
	public Member(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	/**
	 * Getter method for the member's name.
	 * @return Name of the member.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter method to change a member's name.
	 * @param name Member's new name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method for the member's ID.
	 * @return Member's ID.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Setter method to change a member's ID.
	 * @param id Member's new ID.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Getter method for whether the member attended or not.
	 * @return Whether the member attended or not.
	 */
	public boolean isAttended() {
		return attended;
	}

	/**
	 * Setter method to change member's attendance.
	 * @param attended Member's new attendance value.
	 */
	public void setAttended(boolean attended) {
		this.attended = attended;
	}
	
	/**
	 * Method which returns a text with the member details.
	 */
	public void printDetails() {
		System.out.println("Name: " + name + ", id: " + id);
	}
}
