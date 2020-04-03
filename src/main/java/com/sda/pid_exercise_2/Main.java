package com.sda.pid_exercise_2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Members members = new Members();
		Scanner keyboard = new Scanner(System.in);
		members.displayApplicationHeader();
		while(true) {
			members.displayOptionMenu();
			int operation = keyboard.nextInt();
			if(operation == 1) {
				members.showAvailableFiles();
				System.out.println("\n" + "Enter name of member file to open (i.e. example-member-"
						+ "list.json): ");
				keyboard.nextLine();
				String file = keyboard.nextLine();
				try {
					members.loadMemberData(new FileReader(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				members.checkRepeatedID(members.getMemberList());
				members.printMemberData(members.getMemberList());
			} else if(operation == 2) {
				members.checkAttendance();
				members.printMemberData(members.getAttendanceList());
			} else if(operation == 3) {
				System.out.println("\n" + "Enter name of attendance file to be created"
						+ " (i.e. attending-member-list.json): ");
				keyboard.nextLine();
				String file = keyboard.nextLine();
				try {
					members.saveAttendanceData(new FileWriter(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(operation == 4) {
				members.showAvailableFiles();
				System.out.println("\n" + "Enter name of attendance file to open (i.e. attending-"
						+ "member-list.json): ");
				keyboard.nextLine();
				String file = keyboard.nextLine();
				try {
					members.loadAttendanceData(new FileReader(file));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				members.printMemberData(members.getAttendanceList());
			} else if(operation == 5) {
				members.createNewMemberList();
			} else if(operation == 6) {
				System.out.println("Currently active member list: " + "\n");
				members.printMemberData(members.getMemberList());
				System.out.println("\n" + "Enter operation (Add or Remove): ");
				keyboard.nextLine();
				String input = keyboard.nextLine();
				if(input.equals("Add")) {
					members.addMember();
				} else if(input.equals("Remove")) {
					members.removeMember();
				} else {
					System.out.println("Invalid input." + "\n");
				}
				System.out.println("\n" + "Updated member list:" + "\n");
				members.printMemberData(members.getMemberList());
			} else if(operation == 7) {
				System.out.println("\n" + "Enter name of member file to be created "
						+ "(i.e. new-member-list.json): ");
				keyboard.nextLine();
				String file = keyboard.nextLine();
				try {
					members.saveMemberData(new FileWriter(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(operation == 8) {
				System.out.println("Goodbye!");
				break;
			}
		}
		keyboard.close();
	}

}
