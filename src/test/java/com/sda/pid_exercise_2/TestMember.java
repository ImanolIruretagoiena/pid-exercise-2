package com.sda.pid_exercise_2;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class TestMember {

	@Test
	public void testSetName() {
		Member member = new Member("Johan", "g9t6");
		member.setName("Mike");
		assertEquals("Mike", member.getName());
	}
	
	@Test
	public void testSetID() {
		Member member = new Member("Michael", "s4l8");
		member.setId("a4k0");
		assertEquals("a4k0", member.getId());
	}
	
	@Test
	public void testPrintDetails() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Member member = new Member("Michael", "s4l8");
		member.printDetails();
		String expectedOutput = "Name: Michael, id: s4l8" + "\n";
		assertEquals(expectedOutput, output.toString());
		System.setOut(System.out);
	}
}
