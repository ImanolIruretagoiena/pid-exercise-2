package com.sda.pid_exercise_2;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.List;

import org.junit.Test;

/**
 * Test class for Member organizer class.
 * @author ImanolIruretagoiena
 * @version 2020.04.29
 */
public class TestMembers {

	/**
	 * Test which shows unsuccessful attempt to load member data from a non existent file.
	 */
	@Test
	public void testLoadMemberDataFromInvalidFile() {
		Members members = new Members();
		boolean success = false;
		try {
			members.loadMemberData(new FileReader("non-existent-file.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			success = true;
		}
		assertEquals(true, success);
	}
	
	/**
	 * Test which shows successful addition of multiple members to a new member list.
	 */
	@Test
	public void testAddMembersToNewMemberList() {
		String data = "Johan" + "\n" + "o4g8" + "\n" + "Peter" + "\n" + "r6y2";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		Members members = new Members();
		members.addMember();
		members.addMember();
		int result = members.getMemberList().size();
		assertEquals(2, result);
		System.setIn(System.in);
	}
	
	/**
	 * Test which shows how when adding members to a list, if a repeated ID is given only the first
	 * member with that ID will be added.
	 */
	@Test
	public void testCheckRepeatedID() {
		boolean success = false;
		String data = "Johan" + "\n" + "o4g8" + "\n" + "Peter" + "\n" + "o4g8";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Members members = new Members();
		members.addMember();
		members.addMember(); 
		//addMember method doesn't allow repeated IDs, so only the first member is added to the list.
		List<Member> testList = members.getMemberList();
		members.checkRepeatedID(testList);
		if(output.toString().contains("No repeated id-s found.")) {
			success = true;
		}
		assertEquals(true, success);
		System.setIn(System.in);
		System.setOut(System.out);
	}
}
